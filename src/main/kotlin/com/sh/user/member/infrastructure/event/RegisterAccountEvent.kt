package com.sh.user.member.infrastructure.event

import com.sh.user.common.id.IdGenerator
import com.sh.user.member.domain.Member
import com.sh.user.member.domain.MemberId

data class RegisterAccountEvent(
        val email:String,
        val cellphone:String,
        val name:String,
        val nickname:String,
) {
    fun toMember(idGenerator: IdGenerator) : Member {
        return Member(
                id = MemberId(idGenerator.nextId()),
                email = email,
                cellphone = cellphone,
                name = name,
                nickname = nickname)
    }
}
