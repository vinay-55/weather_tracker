package com.example.whethertracker.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.whethertracker.Model.GeminiViewModelFactory
import com.example.whethertracker.Repository.GeminiRepository
import com.example.whethertracker.Server.GeminiApiClient
import com.example.whethertracker.Server.GeminiApiService
import com.example.whethertracker.ViewModel.GeminiViewModel
import com.example.whethertracker.databinding.ActivityFarmerBinding
import kotlin.jvm.java

class FarmerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFarmerBinding
    private lateinit var viewModel: GeminiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFarmerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val apiService = GeminiApiClient().getClient().create(GeminiApiService::class.java)
        val repository = GeminiRepository(apiService)
        val factory = GeminiViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory)[GeminiViewModel::class.java]
        // Menu click
        binding.btnMenu.setOnClickListener {
            val intent = Intent(this, FarmerMenuActivity::class.java)
            startActivity(intent)
        }
        binding.btnSend.setOnClickListener {
            binding.btnSend.isEnabled=false
            binding.tvResponse.text="Loading..."
            val input = binding.etInput.text.toString()

            if (input.isEmpty()) {
                binding.tvResponse.text = "Enter something"
                return@setOnClickListener
            }

            viewModel.getGeminiResponse(
                userInput = input,

                onResult = { response ->
                    binding.tvResponse.append("\n\nYou:$input")
                    binding.tvResponse.append("\n\nBot:$response")

                },

                onError = { error ->
                    binding.tvResponse.text = "Error: $error"
                }
            )
            binding.btnSend.isEnabled=true
        }
    }




}