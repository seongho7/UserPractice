package com.sh.user.cellphoneverification.domain.send

import com.sh.user.cellphoneverification.domain.CellphoneVerification
import com.sh.user.cellphoneverification.domain.SaveVerificationPort
import com.sh.user.common.id.IdGenerator
import java.time.LocalDateTime

class SendTokenService(
        private val saveVerificationPort: SaveVerificationPort,
        private val idGenerator: IdGenerator,
        private val tokenGenerator: TokenGenerator,
        private val tokenSmsSender: TokenSmsSender
        ) : SendTokenUseCase {

    companion object {
        const val TOKEN_EXPIRATION_SECOND = 300L
    }

    override fun send(command: SendTokenCommand): CellphoneVerification {
        val verificationToken = "${tokenGenerator.nextInt()}"
        tokenSmsSender.send(verificationToken)
        val cv = CellphoneVerification(
                id = idGenerator.nextId(),
                cellphone = command.cellphone,
                verificationToken = verificationToken,
                expiredAt = LocalDateTime.now().plusSeconds(TOKEN_EXPIRATION_SECOND)
        )
        return saveVerificationPort.save(cv)
    }
}