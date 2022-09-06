package com.sh.user.cellphoneverification.domain.send

import java.util.concurrent.ThreadLocalRandom

class TokenGenerator {
    private val threadLocalRandom = ThreadLocalRandom.current()
    companion object {
        const val TOKEN_MIN = 100000
        const val TOKEN_MAX = 999999
    }

    fun nextInt() : Int {
        return threadLocalRandom.nextInt(TOKEN_MAX- TOKEN_MIN) + TOKEN_MIN
    }
}