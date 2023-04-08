package com.example.learningfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

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

    override fun onStart() {
        super.onStart()
        var user : FirebaseUser? = FirebaseAuth.getInstance().currentUser
        if (user!= null){
            var intent = Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK    )
            startActivity(intent)
        }
    }

}