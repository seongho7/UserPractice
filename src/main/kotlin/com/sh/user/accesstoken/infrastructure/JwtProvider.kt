package com.sh.user.accesstoken.infrastructure

import com.sh.user.accesstoken.domain.register.AccessTokenProvider
import com.sh.user.account.domain.AccountId
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.impl.DefaultClaims
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtProvider(
        @Value("\${app.access-token.secret}")
        private val tokenSecret:String,
) : AccessTokenProvider {
    private val jwtParser: JwtParser = Jwts.parserBuilder().setSigningKey(getSecretKey()).build()

    override fun createToken(accountId: AccountId, tokenExpirationMsec:Long): String {
        val now = Date()

        val claims = DefaultClaims()
        claims.issuedAt = now
        claims.notBefore = now
        claims.expiration = Date(now.time + tokenExpirationMsec)

        claims.subject = "${accountId.id}"

        return Jwts.builder()
                .setClaims(claims)
                .signWith(getSecretKey())
                .compact()
    }

    override fun parseToken(token: String): AccountId {
        val claims = jwtParser.parseClaimsJws(token)
        return AccountId(claims.body.subject.toLong())
    }

    private fun getSecretKey() : SecretKey {
        val keyBytes = Decoders.BASE64.decode(tokenSecret)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}