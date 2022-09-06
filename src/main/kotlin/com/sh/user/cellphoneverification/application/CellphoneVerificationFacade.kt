package com.sh.user.cellphoneverification.application

import com.sh.user.cellphoneverification.domain.CellphoneVerification
import com.sh.user.cellphoneverification.domain.send.SendTokenCommand
import com.sh.user.cellphoneverification.domain.send.SendTokenUseCase
import org.springframework.stereotype.Service

@Service
class CellphoneVerificationFacade(
        private val sendTokenUseCase: SendTokenUseCase
) {
    fun sendToken(command: SendTokenCommand) : CellphoneVerification {
        return sendTokenUseCase.send(command)
    }
}