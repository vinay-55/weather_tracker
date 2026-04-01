package com.example.whethertracker.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_table")
data class Chat(

    @PrimaryKey(autoGenerate = true)
    val chatId: Int = 0,

    val userId: String, // 🔥 link to Firebase user
    val crop: String,
    val soil:String,

    val createdAt: Long
)