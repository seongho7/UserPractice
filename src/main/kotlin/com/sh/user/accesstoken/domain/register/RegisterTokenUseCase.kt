package com.sh.user.accesstoken.domain.register

import com.sh.user.accesstoken.domain.AccessToken
import com.sh.user.account.domain.AccountId

interface RegisterTokenUseCase {
    fun register(command: RegisterTokenCommand) : AccessToken
}

data class RegisterTokenCommand(
        val accountId:AccountId
)