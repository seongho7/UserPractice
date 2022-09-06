package com.sh.user.accesstoken.infrastructure

import com.sh.user.accesstoken.domain.AccessToken
import com.sh.user.account.domain.AccountId
import org.springframework.data.jpa.repository.JpaRepository

interface AccessTokenJpaRepository : JpaRepository<AccessToken, AccountId> {
}