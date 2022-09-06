package com.sh.user.account.domain

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Index
import javax.persistence.Table

@Table(indexes = [Index(columnList = "loginId", unique = true), Index(columnList = "memberId")])
@Entity
class Account(
        @Column(nullable = false, length = 32)
        val loginId:String,
        @Column(nullable = false, length = 60)
        var password:String,
        @Column(nullable = false)
        val updatedAt:LocalDateTime = LocalDateTime.now(),
        @Column(nullable = false)
        val createdAt:LocalDateTime = LocalDateTime.now(),
        @EmbeddedId
        val id:AccountId,
) {

    @Column(nullable = false)
    var memberId:Long = 0
        private set

    fun addMember(memberId:Long) {
        this.memberId = memberId
    }

    fun modifyPassword(password: String) {
        this.password = password
    }
}