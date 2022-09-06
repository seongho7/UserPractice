package com.sh.user.cellphoneverification.infrastructure

import com.sh.user.cellphoneverification.domain.send.SendTokenService
import com.sh.user.cellphoneverification.domain.send.TokenGenerator
import com.sh.user.cellphoneverification.domain.verify.VerifyTokenService
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Import(value = [
    SendTokenService::class,
    VerifyTokenService::class,
    TokenGenerator::class]
)
@Configuration
class CellphoneVerificationModuleConfig {
}