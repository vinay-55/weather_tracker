package com.example.whethertracker.Repository

import com.example.whethertracker.data.local.Message
import com.example.whethertracker.data.local.MessageDao

class MessageRepository(private val messageDao: MessageDao) {

    suspend fun insert(message: Message) {
        messageDao.insert(message)
    }

    suspend fun getMessages(chatId: Int): List<Message> {
        return messageDao.getMessages(chatId)
    }
}