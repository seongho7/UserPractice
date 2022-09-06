package com.sh.user.cellphoneverification.domain.send

import com.sh.user.cellphoneverification.domain.CellphoneVerification

interface SendTokenUseCase {
    fun send(command: SendTokenCommand) : CellphoneVerification
}

data class SendTokenCommand(
    val cellphone:String,
)