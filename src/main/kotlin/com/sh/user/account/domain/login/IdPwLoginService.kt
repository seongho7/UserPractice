package com.sh.user.account.domain.login

import com.sh.user.accesstoken.domain.AccessToken
import com.sh.user.accesstoken.domain.register.RegisterTokenCommand
import com.sh.user.accesstoken.domain.register.RegisterTokenUseCase
import com.sh.user.account.domain.AccountNotFoundException
import com.sh.user.account.domain.CustomPasswordEncoder
import com.sh.user.account.domain.LoadAccountPort

class IdPwLoginService(
        private val loadAccountPort: LoadAccountPort,
        private val passwordEncoder: CustomPasswordEncoder,
        private val registerTokenUseCase: RegisterTokenUseCase
) : IdPwLoginUseCase {
    override fun login(command: IdPwLoginCommand) : AccessToken {
        val account = loadAccountPort.loadByLoginId(command.loginId).orElseThrow { AccountNotFoundException() }
        if(passwordEncoder.matches(command.rawPassword, account.password).not()) {
            throw LoginFailException()
        }

        return registerTokenUseCase.register(RegisterTokenCommand(accountId = account.id.id))
    }
}