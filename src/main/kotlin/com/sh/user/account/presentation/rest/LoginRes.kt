package com.sh.user.account.presentation.rest

import com.sh.user.accesstoken.domain.AccessToken
import java.time.LocalDateTime

data class LoginRes(
        val accessToken:String,
        val accessTokenExpiredAt: LocalDateTime,
        val refreshToken: String,
        val refreshTokenExpiredAt: LocalDateTime,
) {
    constructor(token: AccessToken) : this(
            accessToken = token.accessToken,
            accessTokenExpiredAt = token.accessTokenExpiredAt,
            refreshToken = token.refreshToken,
            refreshTokenExpiredAt = token.refreshTokenExpiredAt
    )
}