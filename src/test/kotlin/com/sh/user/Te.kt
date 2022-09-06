package com.sh.user

import com.sh.user.common.id.SequenceGenerator
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.ThreadLocalRandom

fun main() {
    println("1234aaaa!".matches(Regex("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[@$!%*#?&])[A-Za-z[0-9]@$!%*#?&]{8,}$")));

    println("010481431751".matches(Regex("^01([0|1|6|7|8|9])?([0-9]{3,4})?([0-9]{4})\$")))
    println(SequenceGenerator.nextId())



}