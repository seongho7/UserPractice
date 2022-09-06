package com.sh.user.common.config

import com.sh.user.account.infrastructure.TokenAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity,
                            tokenAuthenticationFilter: TokenAuthenticationFilter,
                            accessDeniedHandler: CustomAccessDeniedHandler,
                            exceptionHandlingFilter: ExceptionHandlingFilter
    ) : SecurityFilterChain {
        return http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().formLogin().disable()
                .httpBasic().disable()
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
                .addFilterBefore(exceptionHandlingFilter, TokenAuthenticationFilter::class.java)
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .build()
    }
}