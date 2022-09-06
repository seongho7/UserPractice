package com.sh.user.member.infrastructure

import com.sh.user.member.domain.Member
import com.sh.user.member.domain.MemberId
import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository : JpaRepository<Member, MemberId> {
}