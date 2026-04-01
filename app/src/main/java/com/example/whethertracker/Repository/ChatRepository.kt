package com.example.whethertracker.Repository

import com.example.whethertracker.data.local.Chat
import com.example.whethertracker.data.local.ChatDao

class ChatRepository(private val chatDao: ChatDao) {

    suspend fun insertChat(chat: Chat): Long {
        return chatDao.insertChat(chat)
    }

    fun getChatsByUser(userId: String) = chatDao.getChatsByUser(userId)
}