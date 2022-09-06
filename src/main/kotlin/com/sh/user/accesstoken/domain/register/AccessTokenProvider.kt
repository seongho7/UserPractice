package com.sh.user.accesstoken.domain.register

interface AccessTokenProvider {
    fun createToken(accountId: Long, tokenExpirationMsec:Long) : String
    fun parseToken(token:String) : Long
}