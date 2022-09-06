package com.sh.user.account.domain.login

import com.sh.user.accesstoken.domain.AccessToken
import com.sh.user.accesstoken.domain.register.RegisterTokenCommand
import com.sh.user.accesstoken.domain.register.RegisterTokenUseCase
import com.sh.user.account.domain.AccountNotFoundException
import com.sh.user.account.domain.LoadAccountPort
import com.sh.user.cellphoneverification.domain.verify.VerifyTokenCommand
import com.sh.user.cellphoneverification.domain.verify.VerifyTokenUseCase

class CellphoneLoginService(
        private val verifyTokenUseCase: VerifyTokenUseCase,
        private val registerTokenUseCase: RegisterTokenUseCase,
        private val loadAccountPort: LoadAccountPort
) : CellphoneLoginUseCase {
    override fun login(command: CellphoneLoginCommand): AccessToken {
        verifyTokenUseCase.verify(
            VerifyTokenCommand(
                verificationId = command.verificationId,
                cellphone = command.cellphone,
                verificationToken = command.verificationToken
            )
        )
        val account = loadAccountPort.loadByLoginId(command.cellphone).orElseThrow{AccountNotFoundException()}
        return registerTokenUseCase.register(RegisterTokenCommand(account.id.id))
    }
}