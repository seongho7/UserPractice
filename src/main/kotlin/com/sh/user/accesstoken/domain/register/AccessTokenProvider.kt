package com.sh.user.accesstoken.domain.register

import com.sh.user.account.domain.AccountId

interface AccessTokenProvider {
    fun createToken(accountId: AccountId, tokenExpirationMsec:Long) : String
    fun parseToken(token:String) : AccountId
}