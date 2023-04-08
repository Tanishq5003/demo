package com.example.learningfirebase

class Information {

   var email:String = ""
    get() = field
    set(value) {
        field = value
    }

    var name:String?= ""
    get() = field
    set(value) {
        field = value
    }

    constructor(){

    }

    constructor(email: String, name: String) {
        this.email = email
        this.name = name
    }

//    fun getEmail():String{
//        return email.toString()
//    }
//    fun setEmail(email:String) {
//        this.email = email
//    }
//    fun getName():String{
//    return name.toString()
//    }
//    fun setName(name:String){
//        this.name = name
//    }

}