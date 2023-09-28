package com.example.sixpchat.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sixpchat.R
import com.example.sixpchat.models.MessageModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Date


class ChatAdapter(val context: Context,
                  val arrayList: ArrayList<MessageModel>,
                  val recId:String):

    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    private lateinit var SENDER_VIEW_TYPE
    constructor(context: Context,arrayList: ArrayList<MessageModel>):
        this(context,arrayList,"")


    private var SENDER_VIEW_TYPE=1
    private var RECIEVER_VIEW_TYPE=2

    class SendviewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        val sendmsg:TextView=itemView.findViewById(R.id.sendmsg)
        val sendtime :TextView=itemView.findViewById(R.id.sendtime)
    }
    class RecviewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val recmsg:TextView=itemView.findViewById(R.id.recmsg)
        val rextime :TextView=itemView.findViewById(R.id.rectime)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {

        if(viewType == SENDER_VIEW_TYPE){
            val view  =LayoutInflater
                .from(context)
                .inflate(R.layout.sample_sen,parent,false)

            val hol = SendviewHolder(view)
            return  hol
        }
        else
        {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.sample_rec,parent,false)
            return RecviewHolder(view)
        }

    }

    override fun getItemViewType(position: Int): Int {

        if (arrayList.get(position).getuid()
            .equals(FirebaseAuth.getInstance().uid)){
            return SENDER_VIEW_TYPE
        }
        else return RECIEVER_VIEW_TYPE

    }

    override fun getItemCount(): Int {
        return arrayList.size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val msg = arrayList.get(position)

        holder.itemView.setOnLongClickListener {

            val builder=AlertDialog.Builder(context)
            builder.setTitle("Delete")
                .setMessage("Are you sure you want to delete this message")
                .setPositiveButton("Yes",DialogInterface.OnClickListener
                { dialog, i ->

                   val data =  FirebaseDatabase.getInstance()
                    val senderroom = FirebaseAuth.getInstance().uid+recId

                    data.getReference()
                        .child("chats")
                        .child(senderroom)
                        .child(msg.getmsgid())
                        .setValue(null)

                })
                .setNegativeButton("No",DialogInterface.OnClickListener
                { dialog, i ->

                    dialog.dismiss()
                }).show()



            return@setOnLongClickListener false
        }
        when(holder){
            is SendviewHolder ->{

                holder.sendmsg.text=msg.getmsg().toString()
                val date =Date(msg.gettimestmp())
                val sdatefor =SimpleDateFormat(" h:mm a")
                val str = sdatefor.format(date)
                holder.sendtime.text= msg.settimestamp(str.toString()).toString()

            }
            is RecviewHolder ->{

                holder.recmsg.text=msg.getmsg()
                val date =Date(msg.gettimestmp())
                val sdatefor =SimpleDateFormat("h:mm a")
                val str = sdatefor.format(date)
                holder.rextime.text= msg.settimestamp(str.toString()).toString()

            }
        }
    }


}