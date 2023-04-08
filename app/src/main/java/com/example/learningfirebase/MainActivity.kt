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
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.SetOptions
import com.google.firebase.ktx.Firebase
import java.util.Objects

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
//        var authId = FirebaseAuth.getInstance().currentUser

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
                database.child("Name").child(Firebase.auth.currentUser!!.uid).setValue(txt_name)

            }
        }

        var list = ArrayList<String>()
        var adaptr = ArrayAdapter<String>(this , R.layout.list_iem, list)
        listView.adapter = adaptr

        var reference:DatabaseReference = FirebaseDatabase.getInstance("https://learning-firebase-e7f27-default-rtdb.asia-southeast1.firebasedatabase.app/").
                reference.child("Langauges")
        var reference1:DatabaseReference = FirebaseDatabase.getInstance("https://learning-firebase-e7f27-default-rtdb.asia-southeast1.firebasedatabase.app/").
                reference
        reference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){
                    list.clear()
                    for (child in snapshot.children){
                        list.add(child.getValue().toString())
                        adaptr.notifyDataSetChanged()
                        println( child.getValue().toString())
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
            }

        })
        reference1.child("Information").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (child in snapshot.children){
                        var info: Information? = (child.getValue(Information::class.java))
                        var txt:String = info?.name.toString() + " : "+ info?.email.toString()
                        list.add(txt)
                        adaptr.notifyDataSetChanged()
                        println(txt)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
            }

        })

//        Adding data to firebase

    var db:FirebaseFirestore = FirebaseFirestore.getInstance()
//        var city:HashMap<String , String> = HashMap<String, String>()
//        city.put("name", "Bharatpur")
//        city.put("state", "Rajasthan")
//        city.put("country", "India")
//
//        db.collection("cities").document("BTP").set(city).addOnCompleteListener(OnCompleteListener {
//            if (it.isSuccessful){
//                Toast.makeText(this, "Values Added!!", Toast.LENGTH_SHORT).show()
//            }
//        })
//          Merging Data in FireBase
//        var data:HashMap<String, Boolean> = HashMap<String, Boolean>()
//        data.put("Capital", false)
//        db.collection("cities").document("BTP").set(data , SetOptions.merge()).addOnCompleteListener(
//            OnCompleteListener {
//                if (it.isSuccessful){
//                    Toast.makeText(this, "Merge Successful", Toast.LENGTH_SHORT).show()
//                }
//            })
//        Adding data with unique id
//        data.put("Lakshman Mandir", true)
//        db.collection("cities").add(data).addOnCompleteListener(OnCompleteListener {
//            if (it.isSuccessful){
//                Toast.makeText(this, "Values Added Successfully!!", Toast.LENGTH_SHORT).show()
//            }
//        })

//        Updating our Data
        var ref = db.collection("cities").document("BTP")
//        ref.update("Capital", true)

//        Downloading the value from Database

//        ref.get().addOnCompleteListener(OnCompleteListener {
//            if (it.isSuccessful){
//                var doc : DocumentSnapshot = it.getResult()
//                if (doc.exists()){
//                    Log.d("Document", doc.toString())
//                }
//                else{
//                    Log.d("Document", "No Data")
//                }
//            }
//        })

//        Calling specific cities
        FirebaseFirestore.getInstance().collection("cities").whereEqualTo("Capital", true).get().addOnCompleteListener(
            OnCompleteListener {
                if (it.isSuccessful){
                    for (doc:QueryDocumentSnapshot in it.getResult()){
                        Log.d("Document", doc.id+" => "+doc.data)
                    }
                }
            }
        )

    }

    }
