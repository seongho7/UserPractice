package com.sh.user.cellphoneverification.domain.verify

interface VerifyTokenUseCase {
    fun verify(command: VerifyTokenCommand)
}

data class VerifyTokenCommand(
        val verificationId:Long,
        val cellphone:String,
        val verificationToken: String,
)