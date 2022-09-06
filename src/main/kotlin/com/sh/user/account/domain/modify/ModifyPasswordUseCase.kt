package com.sh.user.account.domain.modify

import com.sh.user.account.domain.AccountId

interface ModifyPasswordUseCase {
    fun modify(command: ModifyPasswordCommand)
}

data class ModifyPasswordCommand(
        val accountId: AccountId,
        val rawPassword:String,
)