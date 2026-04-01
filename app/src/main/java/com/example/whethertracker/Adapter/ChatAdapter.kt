package com.example.whethertracker.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.whethertracker.data.local.Chat
import com.example.whethertracker.databinding.ItemChatBinding

class ChatAdapter(private val chatList: List<Chat>,
    private val onClick: (Chat) -> Unit) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(val binding: ItemChatBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ItemChatBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = chatList[position]
        holder.binding.tvChatTitle.text = chat.crop
        holder.itemView.setOnClickListener {
            onClick(chat)
        }
    }

    override fun getItemCount(): Int = chatList.size
}