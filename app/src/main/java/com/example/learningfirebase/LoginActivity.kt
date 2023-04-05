package com.example.learningfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var email:EditText
    private lateinit var password:EditText
    private lateinit var login:Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        login = findViewById(R.id.login)
        auth = FirebaseAuth.getInstance()

        login.setOnClickListener {
            var txt_email:String = email.text.toString()
            var txt_password:String = password.text.toString()
            login_user(txt_email, txt_password)
        }

    }

    private fun login_user(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(this){ task->
            Toast.makeText(this , "Login Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}