package com.sh.user.member.domain

import java.util.Optional

interface SaveMemberPort {
    fun save(member:Member) : Member
}

interface LoadMemberPort {
    fun load(id:MemberId) : Optional<Member>
}