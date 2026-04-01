package com.example.whethertracker.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.whethertracker.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    // 🔹 ViewBinding
    private lateinit var binding: ActivityLoginBinding

    // 🔹 Firebase Auth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (FirebaseAuth.getInstance().currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        // ✅ Initialize binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ Initialize Firebase
        auth = FirebaseAuth.getInstance()

        // 🔹 Login button click
        binding.loginBtn.setOnClickListener {
            loginUser()
        }

        // 🔹 Register button click
        binding.registerBtn.setOnClickListener {
            registerUser()
        }
    }

    // 🔹 LOGIN FUNCTION
    private fun loginUser() {
        val email = binding.emailEt.text.toString().trim()
        val password = binding.passwordEt.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Enter email & password", Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                    // 👉 Move to Main Screen
                    startActivity(Intent(this, FarmerActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Login Failed: ${it.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

    // 🔹 REGISTER FUNCTION
    private fun registerUser() {
        val email = binding.emailEt.text.toString().trim()
        val password = binding.passwordEt.text.toString().trim()

        if (email.isEmpty() || password.length < 6) {
            Toast.makeText(this, "Enter valid email & password (min 6)", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Registration Failed: ${it.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }
}