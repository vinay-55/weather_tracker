package com.example.whethertracker.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ChatDao {

    // 🔹 Insert new chat
    @Insert
    suspend fun insertChat(chat: Chat): Long

    // 🔹 Get chats for specific user
    @Query("SELECT * FROM chat_table WHERE userId = :userId ORDER BY createdAt DESC")
    fun getChatsByUser(userId: String): LiveData<List<Chat>>

    // 🔹 Delete all chats of a user
    @Query("DELETE FROM chat_table WHERE userId = :userId")
    suspend fun deleteAllChats(userId: String)
}