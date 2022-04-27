package com.example.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseauth.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.signInGoogleBtn.setOnClickListener {
            val intent = Intent(this, GoogleRegisterActivity::class.java)
            startActivity(intent)
        }

        binding.signInPhoneBtn.setOnClickListener {
            val intent = Intent(this, PhoneRegisterActivity::class.java)
            startActivity(intent)
        }

    }



}