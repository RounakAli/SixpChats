package com.example.sixpchat.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sixpchat.Chatting
import com.example.sixpchat.R
import com.example.sixpchat.models.Users
import com.squareup.picasso.Picasso

class UserAdapter(val context: Context,val list :ArrayList<Users>):
    RecyclerView.Adapter<UserAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image = itemView.findViewById<ImageView>(R.id.userimage)
        val name = itemView.findViewById<TextView>(R.id.username)
        val lastmsg = itemView.findViewById<TextView>(R.id.lastmsg)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.chat_user,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = list[position]
        Picasso.get().load(user.getprofilepic()).placeholder(R.drawable.avatar3).into(holder.image)
        holder.name.text=user.getusername()

        holder.itemView.setOnClickListener {
            val intent = Intent(context,Chatting::class.java)
            intent.putExtra("userId",user.getuid())
            intent.putExtra("userName",user.getusername())
            intent.putExtra("Photopic",user.getprofilepic())

            context.startActivity(intent)




        }
    }
}