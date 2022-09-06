package com.sh.user.cellphoneverification.domain.verify

import com.sh.user.cellphoneverification.domain.LoadVerificationPort
import com.sh.user.cellphoneverification.domain.SaveVerificationPort
import com.sh.user.common.exception.IllegalStatusException

class VerifyTokenService(
        private val loadVerificationPort: LoadVerificationPort,
        private val saveVerificationPort: SaveVerificationPort
) : VerifyTokenUseCase {
    override fun verify(command: VerifyTokenCommand) {
        val cv = loadVerificationPort.loadById(command.verificationId)
                .orElseThrow { IllegalStatusException("verificationId가 유효하지 않습니다.") }

        if(cv.isInvalidToken(command.verificationToken, command.cellphone)) {
            throw InvalidTokenException()
        }
        cv.verify()
        saveVerificationPort.save(cv)
    }
}