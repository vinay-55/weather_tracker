package com.example.whethertracker.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MessageDao {

    @Insert
    suspend fun insert(message: Message)

    @Query("SELECT * FROM message_table WHERE chatId = :chatId ORDER BY timestamp ASC")
    suspend fun getMessages(chatId: Int): List<Message>
}