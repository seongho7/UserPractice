package com.sh.user.account.domain.modify

import com.sh.user.account.domain.AccountNotFoundException
import com.sh.user.account.domain.CustomPasswordEncoder
import com.sh.user.account.domain.LoadAccountPort
import com.sh.user.account.domain.SaveAccountPort

class ModifyPasswordService(
        private val loadAccountPort: LoadAccountPort,
        private val saveAccountPort: SaveAccountPort,
        private val passwordEncoder: CustomPasswordEncoder
) : ModifyPasswordUseCase {
    override fun modify(command: ModifyPasswordCommand) {
        val account = loadAccountPort.load(command.accountId).orElseThrow { AccountNotFoundException() }
        val accounts = loadAccountPort.loadByMemberId(account.memberId)
        accounts.forEach { it.modifyPassword(passwordEncoder.encode(command.rawPassword)) }
        saveAccountPort.save(accounts)
    }
}