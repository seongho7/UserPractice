package com.sh.user.accesstoken.domain.register

import com.sh.user.accesstoken.domain.AccessToken
import com.sh.user.accesstoken.domain.SaveAccessTokenPort
import java.time.LocalDateTime

class RegisterTokenService(
        private val accessTokenExpirationMsec:Long,
        private val refreshTokenExpirationMsec:Long,
        private val saveAccessTokenPort: SaveAccessTokenPort,
        private val tokenProvider: AccessTokenProvider,
) : RegisterTokenUseCase {

    override fun register(command: RegisterTokenCommand): AccessToken {
        val accessToken = tokenProvider.createToken(command.accountId, accessTokenExpirationMsec)
        val refreshToken = tokenProvider.createToken(command.accountId, refreshTokenExpirationMsec)

        return saveAccessTokenPort.save(
            AccessToken(
                    accountId = command.accountId,
                    accessToken = accessToken,
                    accessTokenExpiredAt = getAccessExpiredAt(),
                    refreshToken = refreshToken,
                    refreshTokenExpiredAt = getRefreshExpiredAt()
            )
        )
    }

    private fun getRefreshExpiredAt() : LocalDateTime {
        val refreshTokenExpirationSec = refreshTokenExpirationMsec/1000
        return LocalDateTime.now().plusSeconds(refreshTokenExpirationSec)
    }

    private fun getAccessExpiredAt() : LocalDateTime {
        val accessTokenExpirationSec = accessTokenExpirationMsec/1000
        return LocalDateTime.now().plusSeconds(accessTokenExpirationSec)
    }
}