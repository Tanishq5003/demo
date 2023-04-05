package com.example.learningfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StartActivity : AppCompatActivity() {

    private lateinit var login:Button
    private lateinit var register:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        login = findViewById(R.id.login)
        register = findViewById(R.id.register)
        register.setOnClickListener {
            var intent = Intent(this , RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
        login.setOnClickListener {
            var intent = Intent(this , LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}