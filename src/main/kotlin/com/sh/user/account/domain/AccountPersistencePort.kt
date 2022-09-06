package com.sh.user.account.domain

import java.util.Optional

interface SaveAccountPort {
    fun save(account: Account) : Account
    fun save(accounts: Collection<Account>): List<Account>
}

interface LoadAccountPort {
    fun load(id:AccountId) : Optional<Account>
    fun loadByLoginId(loginId:String) : Optional<Account>
    fun loadByMemberId(memberId:Long) : List<Account>
}