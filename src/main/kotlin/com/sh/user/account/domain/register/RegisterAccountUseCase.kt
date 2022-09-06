package com.sh.user.account.domain.register

interface RegisterAccountUseCase {
    fun registerAccount(command: RegisterAccountCommand);
}