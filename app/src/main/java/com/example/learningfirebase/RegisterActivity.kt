package com.example.learningfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var email:EditText
    private lateinit var password:EditText
    private lateinit var register:Button
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        register = findViewById(R.id.register)
        auth = FirebaseAuth.getInstance()

        register.setOnClickListener {
            var txt_email:String = email.text.toString()
            var txt_password:String = password.text.toString()
            if (TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_password)){
                Toast.makeText(this , "Empty Credential" , Toast.LENGTH_SHORT).show()
            }
            else if (txt_password.length<6){
                Toast.makeText(this , "Password should contain at least 6 characters" , Toast.LENGTH_SHORT).show()
            }
            else{
                registerUser(txt_email , txt_password)
            }
        }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){task ->
            if (task.isSuccessful()){
                Toast.makeText(this , "User Registered Successfully", Toast.LENGTH_SHORT).show()
                var intent = Intent(this , MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this , "User Registration Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}