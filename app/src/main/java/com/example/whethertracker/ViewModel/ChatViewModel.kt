package com.example.whethertracker.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whethertracker.Repository.ChatRepository
import com.example.whethertracker.data.local.Chat
import kotlinx.coroutines.launch

class ChatViewModel(private val repository: ChatRepository) : ViewModel() {

    fun insertChat(chat: Chat) {
        viewModelScope.launch {
            repository.insertChat(chat)
        }
    }

    fun getChats(userId: String): LiveData<List<Chat>> {
        return repository.getChatsByUser(userId)
    }
}