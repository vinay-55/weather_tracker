package com.example.whethertracker.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_table")
data class Message(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val chatId: Int,
    val message: String,
    val isUser: Boolean,
    val timestamp: Long
)