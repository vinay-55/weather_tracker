package com.example.whethertracker.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.whethertracker.databinding.FarmerMenuBinding

class FarmerMenuActivity : AppCompatActivity() {

    private lateinit var binding: FarmerMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FarmerMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreate.setOnClickListener {

            val crop = binding.etCrop.text.toString()
            val soil = binding.etSoil.text.toString()

            val intent = Intent(this, FarmerActivity::class.java)
            intent.putExtra("crop", crop)
            intent.putExtra("soil", soil)
            startActivity(intent)
        }
    }
}