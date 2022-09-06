package com.sh.user.account.domain.login

import com.sh.user.accesstoken.domain.AccessToken

interface CellphoneLoginUseCase {
    fun login(command:CellphoneLoginCommand) : AccessToken
}

data class CellphoneLoginCommand(
        val verificationId:Long,
        val cellphone:String,
        val verificationToken: String,
)