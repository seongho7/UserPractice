package com.sh.user.account.domain

interface CustomPasswordEncoder {
    fun encode(rawPassword:String) : String
    fun matches(rawPassword:String, encodedPassword:String) : Boolean
}