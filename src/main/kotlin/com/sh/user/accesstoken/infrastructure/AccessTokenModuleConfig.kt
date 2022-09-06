package com.sh.user.accesstoken.infrastructure

import com.sh.user.accesstoken.domain.register.RegisterTokenService
import com.sh.user.accesstoken.domain.register.RegisterTokenUseCase
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AccessTokenModuleConfig {

    @Bean
    fun registerTokenUseCase(@Value("\${app.access-token.expiration-msec}")
                             accessTokenExpirationMsec:Long,
                             @Value("\${app.access-token.refresh-expiration-msec}")
                             refreshTokenExpirationMsec:Long,
                             jwtProvider: JwtProvider,
                             accessTokenPersistenceAdapter: AccessTokenPersistenceAdapter
    ) : RegisterTokenUseCase {
        return RegisterTokenService(
                accessTokenExpirationMsec = accessTokenExpirationMsec,
                refreshTokenExpirationMsec = refreshTokenExpirationMsec,
                saveAccessTokenPort = accessTokenPersistenceAdapter,
                tokenProvider = jwtProvider
        )
    }
}