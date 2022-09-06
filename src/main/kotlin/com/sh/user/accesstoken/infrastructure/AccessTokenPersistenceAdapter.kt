package com.sh.user.accesstoken.infrastructure

import com.sh.user.accesstoken.domain.AccessToken
import com.sh.user.accesstoken.domain.SaveAccessTokenPort
import org.springframework.stereotype.Repository

@Repository
class AccessTokenPersistenceAdapter(
        private val repository: AccessTokenJpaRepository
) : SaveAccessTokenPort {
    override fun save(token: AccessToken): AccessToken {
        return repository.save(token)
    }
}