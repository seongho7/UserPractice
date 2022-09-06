package com.sh.user.cellphoneverification.domain.send

interface TokenSmsSender {
    fun send(token:String)
}