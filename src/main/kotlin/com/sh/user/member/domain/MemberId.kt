package com.sh.user.member.domain

import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
data class MemberId(
        val id:Long,
) : Serializable
