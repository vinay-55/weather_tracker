package com.example.whethertracker.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.whethertracker.Model.GeminiViewModelFactory
import com.example.whethertracker.Model.Project
import com.example.whethertracker.Repository.CropRepository
import com.example.whethertracker.Repository.GeminiRepository
import com.example.whethertracker.Server.GeminiApiClient
import com.example.whethertracker.Server.GeminiApiService
import com.example.whethertracker.ViewModel.CropViewModel
import com.example.whethertracker.ViewModel.CropViewModelFactory
import com.example.whethertracker.ViewModel.GeminiViewModel
import com.example.whethertracker.data.local.AppDatabase
import com.example.whethertracker.data.local.Crop
import com.example.whethertracker.databinding.ActivityFarmerBinding
import kotlinx.coroutines.launch
import kotlin.jvm.java

class FarmerActivity : AppCompatActivity() {
    var useOffline=false
    private lateinit var binding: ActivityFarmerBinding
    private lateinit var viewModel: GeminiViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFarmerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val apiService = GeminiApiClient().getClient().create(GeminiApiService::class.java)
        val repository = GeminiRepository(apiService)
        val factory = GeminiViewModelFactory(repository)
        val db = AppDatabase.getDatabase(this)
        val cropDao = db.cropDao()
        val cropRepository = CropRepository(cropDao)
        val cropFactory = CropViewModelFactory(cropRepository)
        binding.checkBoxOffline.setOnCheckedChangeListener { _, isChecked ->
            useOffline = isChecked
        }

        val cropViewModel = ViewModelProvider(this, cropFactory)[CropViewModel::class.java]

        lifecycleScope.launch {
            cropViewModel.insertIfNotExist(
                Crop(
                    name = "tomato",
                    cropType = "vegetable",
                    growthDays = 90,
                    soilTypes = "loamy",
                    waterNeed = "moderate",
                    sunlight = "full sun",
                    minTemp = 18,
                    maxTemp = 30,
                    plantSpacingCm = "45cm"
                )
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
            updateChatUI()
            binding.etInput.text.clear()
            if (useOffline) {

                lifecycleScope.launch {
                    val crop = cropViewModel.getCropByName(project.crop)

                    if (crop != null) {
                        val response = "Crop: ${crop.name}, Soil: ${crop.soilTypes}, Water: ${crop.waterNeed}"
                        viewModel.addBotMessage(response)
                        updateChatUI()
                        binding.btnSend.isEnabled = true
                    } else {
                        viewModel.addBotMessage("No data in local DB")
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
                        updateChatUI()
                        binding.btnSend.isEnabled = true
                    },

                    onError = { error ->
                        Log.d("api error", error)
                        viewModel.addBotMessage("Error:$error")
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