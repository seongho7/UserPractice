package com.sh.user.accesstoken.infrastructure

import com.sh.user.accesstoken.domain.AccessToken
import org.springframework.data.jpa.repository.JpaRepository

interface AccessTokenJpaRepository : JpaRepository<AccessToken, Long> {
}