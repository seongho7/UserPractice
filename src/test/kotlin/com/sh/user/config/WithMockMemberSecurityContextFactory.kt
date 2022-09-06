package com.sh.user.config

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.context.support.WithSecurityContextFactory

@WithMockUser
class WithMockMemberSecurityContextFactory : WithSecurityContextFactory<WithMockMember> {
    override fun createSecurityContext(annotation: WithMockMember): SecurityContext {
        val context = SecurityContextHolder.createEmptyContext()
        val token = UsernamePasswordAuthenticationToken(annotation.accountId, null, listOf(GrantedAuthority { "ROLE_MEMBER" }))
        context.authentication = token
        return context
    }
}