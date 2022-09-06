package com.sh.user.account.domain.register

import com.sh.user.account.domain.CustomPasswordEncoder
import com.sh.user.account.domain.LoadAccountPort
import com.sh.user.account.domain.SaveAccountPort
import com.sh.user.cellphoneverification.domain.verify.VerifyTokenCommand
import com.sh.user.cellphoneverification.domain.verify.VerifyTokenUseCase
import com.sh.user.common.id.IdGenerator

class RegisterAccountService(
        private val saveAccountPort: SaveAccountPort,
        private val loadAccountPort: LoadAccountPort,
        private val passwordEncoder: CustomPasswordEncoder,
        private val idGenerator: IdGenerator,
        private val verifyTokenUseCase: VerifyTokenUseCase
) : RegisterAccountUseCase {

    override fun registerAccount(command: RegisterAccountCommand) {
        checkExistEmail(command.email)
        checkExistCellphone(command.cellphone)
        verifyCellphone(command)

        val accounts = command.toAccount(passwordEncoder, idGenerator)
        saveAccountPort.save(accounts)
    }

    private fun verifyCellphone(command: RegisterAccountCommand) {
        val verifyTokenCommand = VerifyTokenCommand(
                verificationId = command.verificationId,
                cellphone = command.cellphone,
                verificationToken = command.verificationToken)
        verifyTokenUseCase.verify(verifyTokenCommand)
    }

    private fun checkExistEmail(email:String) {
        val oAccount = loadAccountPort.loadByLoginId(email)
        if(oAccount.isPresent) {
            throw EmailAlreadyExistException()
        }
    }

    private fun checkExistCellphone(cellphone:String) {
        val oAccount = loadAccountPort.loadByLoginId(cellphone)
        if(oAccount.isPresent) {
            throw CellphoneAlreadyExistException()
        }
    }
}