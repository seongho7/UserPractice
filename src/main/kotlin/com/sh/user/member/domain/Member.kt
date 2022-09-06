package com.sh.user.member.domain

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Member(
        @EmbeddedId
        val id:MemberId,
        @Column(nullable = false, length = 32)
        val email:String,
        @Column(nullable = false, length = 16)
        val nickname:String,
        @Column(nullable = false, length = 16)
        val name:String,
        @Column(nullable = false, length = 12)
        val cellphone:String,
        val createdAt:LocalDateTime = LocalDateTime.now(),
) {
}