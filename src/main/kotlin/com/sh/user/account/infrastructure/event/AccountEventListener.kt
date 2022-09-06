package com.sh.user.account.infrastructure.event

import com.sh.user.account.domain.AccountNotFoundException
import com.sh.user.account.domain.LoadAccountPort
import com.sh.user.account.domain.SaveAccountPort
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class AccountEventListener(
        private val loadAccountPort: LoadAccountPort,
        private val saveAccountPort: SaveAccountPort,
) {

    @EventListener
    fun handle(event:RegisterMemberEvent) {
        val cellphoneAccount = loadAccountPort.loadByLoginId(event.cellphone).orElseThrow { AccountNotFoundException() }
        cellphoneAccount.addMember(event.memberId)
        val emailAccount = loadAccountPort.loadByLoginId(event.email).orElseThrow { AccountNotFoundException() }
        emailAccount.addMember(event.memberId)
        saveAccountPort.save(listOf(cellphoneAccount, emailAccount))
    }
}