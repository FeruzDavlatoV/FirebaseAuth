package com.example.firebaseauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.firebaseauth.databinding.ActivityRealtimeStoreBinding
import com.example.firebaseauth.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.lang.StringBuilder

class RealtimeStoreActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var reference: DatabaseReference
    lateinit var binding: ActivityRealtimeStoreBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRealtimeStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

        firebaseDatabase = FirebaseDatabase.getInstance()
        reference= firebaseDatabase.getReference("users")


        val email = currentUser!!.email
        val displayName = currentUser.displayName
        val phoneNumber = currentUser.phoneNumber
        val photoUrl = currentUser.photoUrl
        val uid = currentUser.uid
        var user = User(email, displayName, phoneNumber, photoUrl.toString(),uid)
        reference.child(uid).setValue(user)


        reference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var stringBuilder = StringBuilder()
                val children = snapshot.children
                for (child in children){
                    val value = child.getValue(User::class.java)
                    stringBuilder.append(value?.displayName)
                    Log.d("RealtimeStoreActivity", "onDataChange: ${value?.displayName}")
                }
                binding.menuTv.text = stringBuilder.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}