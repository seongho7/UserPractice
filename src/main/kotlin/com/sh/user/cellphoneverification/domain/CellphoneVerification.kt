package com.sh.user.cellphoneverification.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class CellphoneVerification(
        @Id
        val id:Long,
        @Column(nullable = false, length = 32)
        val cellphone:String,
        @Column(length = 8, nullable = false)
        val verificationToken: String,
        @Column(nullable = false)
        val expiredAt: LocalDateTime,
        @Column(nullable = false)
        val createdAt: LocalDateTime = LocalDateTime.now()
) {
    @Column(nullable = false)
    var verified: Boolean = false
        private set
    var verifiedAt: LocalDateTime? = null
        private set

    fun verify() {
        this.verified = true
        this.verifiedAt = LocalDateTime.now()
    }

    private fun isValidToken(token:String, cellphone: String) : Boolean {
        return this.verificationToken == token
                && expiredAt.isAfter(LocalDateTime.now())
                && this.cellphone == cellphone
    }

    fun isInvalidToken(token:String, cellphone: String) = isValidToken(token, cellphone).not()
}