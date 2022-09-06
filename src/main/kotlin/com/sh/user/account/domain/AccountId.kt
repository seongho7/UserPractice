package com.sh.user.account.domain

import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
data class AccountId(
        val id:Long,
) : Serializable
