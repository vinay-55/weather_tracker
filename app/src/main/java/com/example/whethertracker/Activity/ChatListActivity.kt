package com.example.whethertracker.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whethertracker.Adapter.ChatAdapter
import com.example.whethertracker.Repository.ChatRepository
import com.example.whethertracker.ViewModel.ChatViewModel
import com.example.whethertracker.ViewModel.ChatViewModelFactory
import com.example.whethertracker.data.local.AppDatabase
import com.example.whethertracker.databinding.ActivityChatListBinding
import com.google.firebase.auth.FirebaseAuth

class ChatListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatListBinding
    private lateinit var chatViewModel: ChatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("CHAT_SCREEN","ChatListActivity opened")



        binding = ActivityChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🔹 DB + Repository
        val db = AppDatabase.getDatabase(this)
        val repository = ChatRepository(db.chatDao())

        // 🔹 ViewModel
        chatViewModel = ViewModelProvider(
            this,
            ChatViewModelFactory(repository)
        )[ChatViewModel::class.java]

        // 🔹 Current user
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        // 🔹 Observe chats
        chatViewModel.getChats(userId).observe(this) { chatList ->
            binding.recyclerChats.layoutManager = LinearLayoutManager(this)
            Log.d("CHAT_LIST",chatList.toString())
            binding.recyclerChats.adapter = ChatAdapter(chatList){chat ->
                val intent= Intent(this, FarmerActivity::class.java)
                intent.putExtra("crop",chat.crop)
                intent.putExtra("soil",chat.soil)
                intent.putExtra("chatId",chat.chatId)
                startActivity(intent)

            }

        }
    }
}