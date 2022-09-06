package com.sh.user.account.infrastructure

import com.sh.user.account.domain.login.CellphoneLoginService
import com.sh.user.account.domain.login.IdPwLoginService
import com.sh.user.account.domain.modify.ModifyPasswordService
import com.sh.user.account.domain.register.RegisterAccountService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Import(value = [
    RegisterAccountService::class,
    IdPwLoginService::class,
    CellphoneLoginService::class,
    ModifyPasswordService::class
])
@Configuration
class AccountModuleConfig {

    @Bean
    fun passwordEncoder() : PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}