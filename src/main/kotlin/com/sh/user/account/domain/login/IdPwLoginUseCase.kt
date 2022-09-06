package com.sh.user.account.domain.login

import com.sh.user.accesstoken.domain.AccessToken

interface IdPwLoginUseCase {
    fun login(command:IdPwLoginCommand) : AccessToken
}

data class IdPwLoginCommand(
        val loginId:String,
        val rawPassword:String
)