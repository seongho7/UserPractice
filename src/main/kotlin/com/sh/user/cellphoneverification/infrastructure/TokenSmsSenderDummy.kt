package com.sh.user.cellphoneverification.infrastructure

import com.sh.user.cellphoneverification.domain.send.TokenSmsSender
import org.springframework.stereotype.Component

@Component
class TokenSmsSenderDummy : TokenSmsSender {
    override fun send(token: String) {

    }
}