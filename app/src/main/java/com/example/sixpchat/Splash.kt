package com.example.sixpchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.FirebaseAuth

class Splash : AppCompatActivity() {

    private lateinit var mauth :FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        mauth = FirebaseAuth.getInstance()
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            if(mauth.currentUser!=null){
                val intent  = Intent(this,MainActivity::class.java)
            }
        },3000

        )

    }
}