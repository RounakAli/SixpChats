package com.example.sixpchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import com.example.sixpchat.databinding.ActivitySignUpBinding
import com.example.sixpchat.models.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.values

class SignUp : AppCompatActivity() {

    private lateinit var mauth:FirebaseAuth
    private lateinit var binding:ActivitySignUpBinding
    private lateinit var database :FirebaseDatabase
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        mauth =FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance()

        progressBar=ProgressBar(this)

        binding.btnsignup.setOnClickListener {

            if (binding.txtusername.text.toString().isNotEmpty()||
                binding.password.text.toString().isNotEmpty()||
                binding.email.text.toString().isNotEmpty() ){

                mauth.createUserWithEmailAndPassword(binding.email.text.toString(),
                    binding.password.text.toString())
                    .addOnCompleteListener {

                        if (it.isComplete){

                            val user = Users(binding.email.text.toString(),
                                binding.email.text.toString(),
                                binding.password.text.toString())

                            val id = it.result.user?.uid.toString()
                            Toast.makeText(this,
                                "User Registered Successfully",
                                Toast.LENGTH_SHORT)
                                .show()

                            database.getReference().child("Users").child(id).setValue(user)
                        }
                        else
                        {
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT)
                                .show()

                        }
                    }
            }

            else
            {
                Toast.makeText(this,"Enter credentials",Toast.LENGTH_SHORT)
                    .show()
            }
        }





    }
}