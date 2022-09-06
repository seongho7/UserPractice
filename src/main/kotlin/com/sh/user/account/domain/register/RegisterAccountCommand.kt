package com.sh.user.account.domain.register

import com.sh.user.account.domain.Account
import com.sh.user.account.domain.AccountId
import com.sh.user.account.domain.CustomPasswordEncoder
import com.sh.user.common.id.IdGenerator
import javax.validation.constraints.NotBlank

data class RegisterAccountCommand(
        val email:String,
        val cellphone:String,
        val verificationId:Long,
        val verificationToken:String,
        val rawPassword:String,
        val name:String,
        val nickname:String,
) {
    fun toAccount(passwordEncoder: CustomPasswordEncoder, idGenerator: IdGenerator) : List<Account> {
        val emailAccount = Account(
                loginId = email,
                password = passwordEncoder.encode(rawPassword),
                id = AccountId(idGenerator.nextId()))
        val cellphoneAccount = Account(
                loginId = cellphone,
                password = passwordEncoder.encode(rawPassword),
                id = AccountId(idGenerator.nextId()))

        return listOf(emailAccount, cellphoneAccount)
    }

    fun toEvent() : RegisterAccountEvent {
        return RegisterAccountEvent(email = email, cellphone = cellphone, name = name, nickname = nickname)
    }
}