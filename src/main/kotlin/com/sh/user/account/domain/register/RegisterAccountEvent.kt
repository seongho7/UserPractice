package com.sh.user.account.domain.register

data class RegisterAccountEvent(
        val email:String,
        val cellphone:String,
        val name:String,
        val nickname:String,
)