package com.example.sixpchat.models

import java.sql.Time

data class MessageModel(var uid:String,
                        var message:String,
                        var messageId:String,
                        var TimeStamp:Long){
    constructor(uid: String,message: String):this(uid,message,"",0L)
    constructor(uid: String,message: String,TimeStamp: Long):this(uid,message,"",TimeStamp)

    fun getuid():String{
        return uid
    }


    fun getmsg():String{
        return message
    }


    fun getmsgid():String{
        return messageId
    }

    fun gettimestmp():Long{
        return TimeStamp
    }

    fun settimestamp(TimeStamp: String){
        this.TimeStamp= TimeStamp.toLong()
    }
    fun setmsg(message: String){
        this.message=message
    }

    fun setuid(uid: String){
        this.uid=uid
    }

   fun setmsgid(messageId: String){
       this.messageId=messageId
   }




}
