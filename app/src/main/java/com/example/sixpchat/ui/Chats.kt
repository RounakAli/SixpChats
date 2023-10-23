package com.example.sixpchat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sixpchat.R
import com.example.sixpchat.adapters.UserAdapter
import com.example.sixpchat.databinding.FragmentChatsBinding
import com.example.sixpchat.models.Users
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Chats : Fragment() {

    private lateinit var binding: FragmentChatsBinding
    private lateinit var list:ArrayList<Users>
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentChatsBinding.inflate(layoutInflater,container,false)
        database= FirebaseDatabase.getInstance()

        val adapter=UserAdapter(requireContext(),list)
        binding.recview.adapter=adapter

        binding.recview.layoutManager=LinearLayoutManager(context)


        val addValueEventListener = database.getReference().child("Users")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    list.clear()
                    for (datasnapshot in snapshot.children){
                        val users =datasnapshot.getValue(Users::class.java)
                        datasnapshot.key?.let { users?.setUserId(it) }
                        if (users != null) {
                            list.add(users)
                        }
                        adapter.notifyDataSetChanged()
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

        return binding.root
    }

    companion object {

    }
}
