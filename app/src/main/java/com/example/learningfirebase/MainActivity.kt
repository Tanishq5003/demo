package com.example.learningfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.NonNull
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var logout: Button
    private lateinit var edit: EditText
    private lateinit var add: Button
    private lateinit var database: DatabaseReference
    private lateinit var listView:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logout = findViewById(R.id.logout)
        add = findViewById(R.id.add)
        edit = findViewById(R.id.name)
        database = Firebase.database("https://learning-firebase-e7f27-default-rtdb.asia-southeast1.firebasedatabase.app/").reference
        listView = findViewById(R.id.lstvw)

        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(this, "Logged out Successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, StartActivity::class.java))
            finish()
        }



        add.setOnClickListener {
            var txt_name = edit.text.toString()
            if (txt_name.isEmpty()) {
                Toast.makeText(this, "No Name Entered", Toast.LENGTH_SHORT).show()
            } else {
                database.child("Name").push().setValue(txt_name)

            }
        }

        var list = ArrayList<String>()
        var adaptr = ArrayAdapter<String>(this , R.layout.list_iem, list)
        listView.adapter = adaptr

        var reference:DatabaseReference = FirebaseDatabase.getInstance("https://learning-firebase-e7f27-default-rtdb.asia-southeast1.firebasedatabase.app/").
                getReference("Langauge")



    }

    }
