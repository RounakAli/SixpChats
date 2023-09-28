package com.example.sixpchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sixpchat.adapters.ChatAdapter
import com.example.sixpchat.databinding.ActivityChattingBinding
import com.example.sixpchat.models.MessageModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import java.util.Date

class Chatting : AppCompatActivity() {

    private  lateinit var binding:ActivityChattingBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var mauth :FirebaseAuth
    private lateinit var list :ArrayList<MessageModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        database = FirebaseDatabase.getInstance()
        mauth = FirebaseAuth.getInstance()
        val senderId =mauth.uid

        val recieveId = intent.getStringExtra("userId")
        val username = intent.getStringExtra("userName")

        val photopic = intent.getStringExtra("Photopic")

        binding.proname.text = username.toString()
        Picasso.get().load(photopic).placeholder(R.drawable.avatar).into(binding.proimage)

        binding.backarrow.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
        }

        val adapter=ChatAdapter(this,list,recieveId!!)

        val lm = LinearLayoutManager(this)
        binding.chatrecview.layoutManager =lm

        val Senderroom =senderId+recieveId
        val Recieveroom = recieveId+senderId

        database.getReference().child("Chats")
            .child(Senderroom)
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    list.clear()
                    for (datasnapshot in snapshot.children)
                    {
                        val model= datasnapshot.getValue(MessageModel::class.java)
                        model?.setmsgid(snapshot.key!!)
                        list.add(model!!)
                    }


                }

                override fun onCancelled(error: DatabaseError) {

                }


            })



        binding.send.setOnClickListener {
            val msg= binding.msg.text.toString()

            val model=MessageModel(senderId!!,msg)
            model.settimestamp(Date().time.toString())
            binding.msg.text=""

            database.getReference().child("chats")
                .child(Recieveroom)
                .push()
                .setValue(model).addOnSuccessListener {

                    database.getReference().child("chats")
                        .child(Senderroom)
                        .push()
                        .setValue(model).addOnSuccessListener {

                        }
                }



        }




    }
}