package com.sh.user.account.application

import com.sh.user.accesstoken.domain.AccessToken
import com.sh.user.account.domain.Account
import com.sh.user.account.domain.AccountId
import com.sh.user.account.domain.AccountNotFoundException
import com.sh.user.account.domain.LoadAccountPort
import com.sh.user.account.domain.login.CellphoneLoginCommand
import com.sh.user.account.domain.login.CellphoneLoginUseCase
import com.sh.user.account.domain.login.IdPwLoginUseCase
import com.sh.user.account.domain.login.IdPwLoginCommand
import com.sh.user.account.domain.modify.ModifyPasswordCommand
import com.sh.user.account.domain.modify.ModifyPasswordUseCase
import com.sh.user.account.domain.register.RegisterAccountCommand
import com.sh.user.account.domain.register.RegisterAccountUseCase
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate

@Service
class AccountFacade(
        private val registerAccountUseCase: RegisterAccountUseCase,
        private val idPwLoginUseCase: IdPwLoginUseCase,
        private val cellphoneLoginUseCase: CellphoneLoginUseCase,
        private val eventPublisher: ApplicationEventPublisher,
        private val transactionTemplate: TransactionTemplate,
        private val loadAccountPort: LoadAccountPort,
        private val modifyPasswordUseCase: ModifyPasswordUseCase
) {

    fun registerAccount(command: RegisterAccountCommand) {
        registerAccountUseCase.registerAccount(command)

        eventPublisher.publishEvent(command.toEvent())
    }

    fun login(command: IdPwLoginCommand) : AccessToken {
        return idPwLoginUseCase.login(command)
    }

    fun login(command:CellphoneLoginCommand) : AccessToken {
        return cellphoneLoginUseCase.login(command)
    }

    fun load(id: AccountId) : Account {
        return loadAccountPort.load(id).orElseThrow { AccountNotFoundException() }
    }

    fun modifyPassword(command:ModifyPasswordCommand) {
        modifyPasswordUseCase.modify(command)
    }
}