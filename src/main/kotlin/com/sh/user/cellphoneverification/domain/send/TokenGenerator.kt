package com.sh.user.cellphoneverification.domain.send

import java.util.concurrent.ThreadLocalRandom

class TokenGenerator {
    companion object {
        const val TOKEN_MIN = 100000
        const val TOKEN_MAX = 999999
    }

    fun nextInt() : Int {
        return ThreadLocalRandom.current().nextInt(TOKEN_MAX- TOKEN_MIN) + TOKEN_MIN
    }
}