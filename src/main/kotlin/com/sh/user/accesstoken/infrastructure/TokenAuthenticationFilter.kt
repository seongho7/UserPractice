package com.sh.user.accesstoken.infrastructure

import com.sh.user.accesstoken.domain.register.AccessTokenProvider
import com.sh.user.account.domain.AccountId
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class TokenAuthenticationFilter(
        private val tokenProvider: AccessTokenProvider
) : OncePerRequestFilter() {
    override fun doFilterInternal(
            request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

        val jwt = getJwtFromRequest(request)
        if (jwt != null) {
            val accountId:Long = tokenProvider.parseToken(jwt)
            val authentication = UsernamePasswordAuthenticationToken(AccountId(accountId), jwt, listOf(GrantedAuthority { "ROLE_MEMBER" }))
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

    private fun getJwtFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION)
        return when {
            StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ") -> {
                bearerToken.substring(7, bearerToken.length)
            }
            else -> null
        }
    }
}