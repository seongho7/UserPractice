package com.sh.user.cellphoneverification.domain

import java.util.Optional

interface SaveVerificationPort {
    fun save(cv:CellphoneVerification) : CellphoneVerification
}

interface LoadVerificationPort {
    fun loadById(id:Long) : Optional<CellphoneVerification>
}