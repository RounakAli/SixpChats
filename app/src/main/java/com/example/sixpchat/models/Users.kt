package com.example.sixpchat.models

data class Users(
    var name :String,
    var email:String,
    var password:String,
    var profilepic:String,
    var lstmsg:String,
    var status:String,
    var uid: String

) {
    constructor(name: String, email: String, password: String) :
            this(name, email, password, "","","",""){}
    constructor(name: String, email: String) :
            this(name, email, "", "","","",""){}
    constructor(name: String, email: String, password: String,profilepic: String) :
            this(name, email, password, profilepic,"","",""){}

    fun getprofilepic ():String {
        return profilepic
    }

    fun getusername():String
    {
        return name
    }

    fun setUserId(uid:String) {
        this.uid=uid
    }

    fun getuid():String
    {
        return uid
    }
}
