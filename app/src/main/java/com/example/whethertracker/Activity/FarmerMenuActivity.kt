package com.example.whethertracker.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.whethertracker.Repository.ChatRepository
import com.example.whethertracker.data.local.AppDatabase
import com.example.whethertracker.data.local.Chat
import com.example.whethertracker.databinding.FarmerMenuBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class FarmerMenuActivity : AppCompatActivity() {

    private lateinit var binding: FarmerMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FarmerMenuBinding.inflate(layoutInflater)
        val user= FirebaseAuth.getInstance().currentUser
        if(user==null){
            binding.btnChats.visibility= View.GONE
        }
        setContentView(binding.root)
        binding.btnChats.setOnClickListener {
            startActivity(Intent(this, ChatListActivity::class.java))
        }
        binding.btnWeather.setOnClickListener {
            val intent=Intent(this, WeatherActivity::class.java)
            startActivity(intent)
        }
        binding.btnProfile.setOnClickListener {

            val user = FirebaseAuth.getInstance().currentUser

            if (user == null) {
                // Not logged in → go to login
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                // Logged in → logout
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        binding.btnCreate.setOnClickListener {

            val crop = binding.etCrop.text.toString().trim().lowercase()
            val soil = binding.etSoil.text.toString().trim().lowercase()
            val user = FirebaseAuth.getInstance().currentUser

            if (user == null) {
                Toast.makeText(this, "Please login first", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                return@setOnClickListener
            }

            val userId = user.uid

            if (crop.isEmpty() || soil.isEmpty()) {
                Toast.makeText(this, "Enter all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(this, "Project created open chats", Toast.LENGTH_SHORT).show()
            val db = AppDatabase.getDatabase(this)
            val repository = ChatRepository(db.chatDao())

            lifecycleScope.launch {
                val chatId=
                repository.insertChat(
                    Chat(
                        userId = userId,
                        crop = crop,
                        soil = soil,
                        createdAt = System.currentTimeMillis()
                    )
                ).toInt()
                Log.d("CHAT_DEBUG", "Inserted chat: $crop")

                // 🔥 AFTER insert → navigate
               /* val intent = Intent(this@FarmerMenuActivity, FarmerActivity::class.java)
                intent.putExtra("crop", crop)
                intent.putExtra("soil", soil)
                startActivity(intent)*/
            }
        }
    }
}