package com.sh.user.member.domain

data class RegisterMemberEvent(
        val memberId: MemberId,
        val email:String,
        val cellphone:String
)