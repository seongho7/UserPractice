package com.sh.user.accesstoken.domain

import java.time.LocalDateTime
import javax.persistence.*

@Table(indexes = [
    Index(columnList = "refreshToken")
])
@Entity
class AccessToken (
        @Id
    val accountId:Long,
        @Column(length = 400, nullable = false)
    val accessToken: String,
        @Column(nullable = false)
    val accessTokenExpiredAt:LocalDateTime,
        @Column(length = 400, nullable = false)
    val refreshToken: String,
        @Column(nullable = false)
    var refreshTokenExpiredAt: LocalDateTime,
) {
}