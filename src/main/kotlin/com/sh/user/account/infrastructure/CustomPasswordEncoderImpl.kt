package com.sh.user.account.infrastructure

import com.sh.user.account.domain.CustomPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class CustomPasswordEncoderImpl(
        private val passwordEncoder: PasswordEncoder
) : CustomPasswordEncoder {
    override fun encode(rawPassword: String): String {
        return passwordEncoder.encode(rawPassword)
    }

    override fun matches(rawPassword: String, encodedPassword: String): Boolean {
        return passwordEncoder.matches(rawPassword, encodedPassword)
    }
}