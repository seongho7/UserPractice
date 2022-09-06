package com.sh.user.accesstoken.domain.register

import com.sh.user.accesstoken.domain.AccessToken

interface RegisterTokenUseCase {
    fun register(command: RegisterTokenCommand) : AccessToken
}

data class RegisterTokenCommand(
        val accountId:Long
)