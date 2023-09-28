package com.example.sixpchat

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sixpchat.databinding.ActivitySignInBinding
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {

    private lateinit var mauth: FirebaseAuth
    private lateinit var binding: ActivitySignInBinding
    private lateinit var database : FirebaseDatabase
    private lateinit var progressBar: ProgressBar
    private val REQ_ONE_TAP = 2  // Can be any integer unique to the Activity
    private var showOneTapUI = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        mauth= FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance()

    /*    var signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId(getString(R.string.your_web_client_id))
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(true)
                    .build()
            )
            .build()*/

        binding.btnsignin.setOnClickListener {

            if (binding.email.text.toString().isNotEmpty()||
                binding.password.text.toString().isNotEmpty()){

                mauth.signInWithEmailAndPassword(
                    binding.email.text.toString(),
                    binding.password.text.toString())
                    .addOnCompleteListener {

                        if (it.isComplete){
                            val intent =Intent(this,MainActivity::class.java)
                            startActivity(intent)
                            finish()

                        }
                        else{
                            Toast.makeText(this,
                                it.exception?.message,
                                Toast.LENGTH_SHORT)
                                .show()

                        }

                    }

            }
            else{
                Toast.makeText(this,"Enter the Details",Toast.LENGTH_SHORT)

            }

        }

    }
   /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQ_ONE_TAP -> {
                try {
                    val credential = oneTapClient.getSignInCredentialFromIntent(data)
                    val idToken = credential.googleIdToken
                    when {
                        idToken != null -> {
                            // Got an ID token from Google. Use it to authenticate
                            // with Firebase.
                            Log.d(TAG, "Got ID token.")
                        }

                        else -> {
                            // Shouldn't happen.
                            Log.d(TAG, "No ID token!")
                        }
                    }
                } catch (e: ApiException) {
                    // ...
                }
            }
        }
        val googleCredential = oneTapClient.getSignInCredentialFromIntent(data)
        val idToken = googleCredential.googleIdToken
    when {
    idToken != null -> {
        // Got an ID token from Google. Use it to authenticate
        // with Firebase.
        val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(firebaseCredential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        updateUI(null)
                    }
                }
    }
    else -> {
        // Shouldn't happen.
        Log.d(TAG, "No ID token!")
    }
}
    }*/
}