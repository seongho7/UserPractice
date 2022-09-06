package com.sh.user.accesstoken.domain

import com.sh.user.account.domain.AccountId
import java.time.LocalDateTime
import javax.persistence.*

@Table(indexes = [
    Index(columnList = "refreshToken")
])
@Entity
class AccessToken (
        @EmbeddedId
    val accountId:AccountId,
        @Column(length = 400, nullable = false)
    val accessToken: String,
        @Column(nullable = false)
    val accessTokenExpiredAt:LocalDateTime,
        @Column(length = 400, nullable = false)
    val refreshToken: String,
        @Column(nullable = false)
    var refreshTokenExpiredAt: LocalDateTime,
) {

    fun checkExpired() {
        //if(LocalDateTime.now().isAfter(refreshExpiredAt)) throw ExpiredTokenException()
    }

    fun expire() {
        refreshTokenExpiredAt = LocalDateTime.now()
    }
}