package com.example.whethertracker.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.whethertracker.ViewModel.GeminiViewModelFactory
import com.example.whethertracker.Model.Project
import com.example.whethertracker.Repository.ChatRepository
import com.example.whethertracker.Repository.CropRepository
import com.example.whethertracker.Repository.GeminiRepository
import com.example.whethertracker.Repository.MessageRepository
import com.example.whethertracker.Server.GeminiApiClient
import com.example.whethertracker.Server.GeminiApiService
import com.example.whethertracker.ViewModel.ChatViewModel
import com.example.whethertracker.ViewModel.ChatViewModelFactory
import com.example.whethertracker.ViewModel.CropViewModel
import com.example.whethertracker.ViewModel.CropViewModelFactory
import com.example.whethertracker.ViewModel.GeminiViewModel
import com.example.whethertracker.data.local.AppDatabase
import com.example.whethertracker.data.local.Chat
import com.example.whethertracker.data.local.Crop
import com.example.whethertracker.data.local.Message
import com.example.whethertracker.data.seed.CropSeeder
import com.example.whethertracker.databinding.ActivityFarmerBinding
import com.example.whethertracker.utils.ChatHelper
import com.example.whethertracker.utils.ChatResponseBuilder
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlin.jvm.java

class FarmerActivity : AppCompatActivity() {
    var useOffline=false
    private lateinit var binding: ActivityFarmerBinding
    private lateinit var viewModel: GeminiViewModel
    private lateinit var chatViewModel: ChatViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val messageRepository = MessageRepository(AppDatabase.getDatabase(this).messageDao())
        val chatId: Int = intent.getIntExtra("chatId", -1)
        Log.d("CHAT_ID","chatId=$chatId")
        if (chatId != -1) {

            lifecycleScope.launch {

                val messages = messageRepository.getMessages(chatId)

                messages.forEach {
                    if (it.isUser) {
                        viewModel.addUserMessage(it.message)
                    } else {
                        viewModel.addBotMessage(it.message)
                    }
                }

                updateChatUI()
            }
        }


        binding = ActivityFarmerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {

            val messages = messageRepository.getMessages(chatId)

            messages.forEach {

                if (it.isUser) {
                    viewModel.addUserMessage(it.message)
                } else {
                    viewModel.addBotMessage(it.message)
                }
            }

            updateChatUI()
        }
        val apiService = GeminiApiClient().getClient().create(GeminiApiService::class.java)
        val repository = GeminiRepository(apiService)
        val factory = GeminiViewModelFactory(repository)
        val db = AppDatabase.getDatabase(this)
        val cropDao = db.cropDao()
        val cropRepository = CropRepository(cropDao)
        val cropFactory = CropViewModelFactory(cropRepository)
        val chatDao = db.chatDao()
        val chatRepository = ChatRepository(chatDao)

        chatViewModel = ViewModelProvider(
            this,
            ChatViewModelFactory(chatRepository)
        )[ChatViewModel::class.java]
        binding.checkBoxOffline.setOnCheckedChangeListener { _, isChecked ->
            useOffline = isChecked
        }
        binding.profileIcon.setOnClickListener {

            val user = FirebaseAuth.getInstance().currentUser

            if (user == null) {
                // Not logged in → go to login
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                // Logged in → logout
                FirebaseAuth.getInstance().signOut()

                Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        val cropViewModel = ViewModelProvider(this, cropFactory)[CropViewModel::class.java]

        lifecycleScope.launch {
            CropSeeder.seed(cropViewModel
            )
        }

        viewModel = ViewModelProvider(this, factory)[GeminiViewModel::class.java]
        // Menu click
        binding.btnMenu.setOnClickListener {
            val intent = Intent(this, FarmerMenuActivity::class.java)
            startActivity(intent)
        }
        val crop = intent.getStringExtra("crop")
        val soil = intent.getStringExtra("soil")

        if (crop != null && soil != null) {
            val project = Project(crop, soil)
            viewModel.setProject(project)
        }
        binding.btnSend.setOnClickListener {

            binding.btnSend.isEnabled=false
            if (viewModel.currentProject == null) {
                binding.tvResponse.text = "⚠️ Please create a project first"
                return@setOnClickListener
            }
            val input = binding.etInput.text.toString()
            if (input.isEmpty()) {
                binding.tvResponse.text = "Enter something"
                binding.btnSend.isEnabled = true
                return@setOnClickListener
            }
            val project=viewModel.currentProject!!
            val finalprompt="""
                Crop:${project.crop}soil:${project.soil}question:$input
                Give short practical advice.""".trimIndent()
            viewModel.addUserMessage(input)
            lifecycleScope.launch {
                messageRepository.insert(
                    Message(
                        chatId = chatId,
                        message = input,
                        isUser = true,
                        timestamp = System.currentTimeMillis()
                    )
                )
            }
            val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return@setOnClickListener

            updateChatUI()
            binding.etInput.text.clear()
            if (useOffline) {

                lifecycleScope.launch {
                    val crop = cropViewModel.getCropByName(project.crop)

                    if (crop != null) {
                        val intent = ChatHelper.detectIntent(input)

                        val response = ChatResponseBuilder.buildResponse(intent,input,crop)
                        viewModel.addBotMessage(response)
                        lifecycleScope.launch {
                            messageRepository.insert(
                                Message(
                                    chatId = chatId,
                                    message = response,
                                    isUser = false,
                                    timestamp = System.currentTimeMillis()
                                )
                            )
                        }
                        updateChatUI()
                        binding.btnSend.isEnabled = true
                    } else {
                        viewModel.addBotMessage("No data in local DB")
                        lifecycleScope.launch {
                            messageRepository.insert(
                                Message(
                                    chatId = chatId,
                                    message = "No data in local DB",
                                    isUser = false,
                                    timestamp = System.currentTimeMillis()
                                )
                            )
                        }
                        updateChatUI()
                        binding.btnSend.isEnabled = true
                    }
                }

            }
            else {
                viewModel.getGeminiResponse(
                    userInput = finalprompt,

                    onResult = { response ->
                        Log.d("api success", response)
                        println("DEBUG: $response")
                        viewModel.addBotMessage(response)
                        lifecycleScope.launch {
                            messageRepository.insert(
                                Message(
                                    chatId = chatId,
                                    message = response,
                                    isUser = false,
                                    timestamp = System.currentTimeMillis()
                                )
                            )
                        }
                        updateChatUI()
                        binding.btnSend.isEnabled = true
                    },

                    onError = { error ->
                        Log.d("api error", error)
                        viewModel.addBotMessage("Error:$error")
                        lifecycleScope.launch {
                            messageRepository.insert(
                                Message(
                                    chatId = chatId,
                                    message = error,
                                    isUser = false,
                                    timestamp = System.currentTimeMillis()
                                )
                            )
                        }
                        updateChatUI()
                        binding.btnSend.isEnabled = true
                    }
                )
            }
        }
    }
    private fun updateChatUI() {

        val chatText = viewModel.chatList.joinToString("\n\n") {

            if (it.isUser) {
                "👤 You: ${it.message}"
            } else {
                "🤖 Bot: ${it.message}"
            }
        }

        binding.tvResponse.text = chatText

        // Auto scroll (important)
        binding.scrollView.post {
            binding.scrollView.fullScroll(View.FOCUS_DOWN)
        }
    }
}