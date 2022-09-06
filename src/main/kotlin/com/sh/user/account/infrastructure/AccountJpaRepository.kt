package com.sh.user.account.infrastructure

import com.sh.user.account.domain.Account
import com.sh.user.account.domain.AccountId
import com.sh.user.member.domain.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface AccountJpaRepository : JpaRepository<Account, AccountId> {
    fun findByLoginId(loginId:String) : Optional<Account>
    fun findByMemberId(memberId:Long) : List<Account>
}