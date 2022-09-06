package com.sh.user.accesstoken.domain

interface SaveAccessTokenPort {
    fun save(token:AccessToken) : AccessToken
}