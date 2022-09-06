package com.sh.user.member.application

import com.sh.user.member.domain.LoadMemberPort
import com.sh.user.member.domain.Member
import com.sh.user.member.domain.MemberId
import com.sh.user.member.domain.MemberNotFoundException
import org.springframework.stereotype.Service

@Service
class MemberFacade(
    private val loadMemberPort: LoadMemberPort
) {
    fun load(id: MemberId) : Member {
        return loadMemberPort.load(id).orElseThrow { MemberNotFoundException("존재하지 않는 회원입니다.") }
    }
}