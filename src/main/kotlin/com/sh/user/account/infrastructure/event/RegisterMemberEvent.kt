package com.sh.user.account.infrastructure.event

data class RegisterMemberEvent(
        val memberId:Long,
        val email:String,
        val cellphone:String,
)
