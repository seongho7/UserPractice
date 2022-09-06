package com.sh.user.account.infrastructure

import com.sh.user.account.domain.Account
import com.sh.user.account.domain.AccountId
import com.sh.user.account.domain.LoadAccountPort
import com.sh.user.account.domain.SaveAccountPort
import com.sh.user.member.domain.Member
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
class AccountPersistenceAdapter(
        private val jpaRepository: AccountJpaRepository
) : SaveAccountPort, LoadAccountPort {
    override fun save(account: Account): Account {
        return jpaRepository.save(account)
    }

    override fun save(accounts: Collection<Account>): List<Account> {
        return jpaRepository.saveAll(accounts)
    }

    override fun load(id: AccountId): Optional<Account> {
        return jpaRepository.findById(id)
    }

    override fun loadByLoginId(loginId: String): Optional<Account> {
        return jpaRepository.findByLoginId(loginId)
    }

    override fun loadByMemberId(memberId: Long) : List<Account> {
        return jpaRepository.findByMemberId(memberId)
    }
}