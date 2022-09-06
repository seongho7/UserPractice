package com.sh.user.member.infrastructure

import com.sh.user.member.domain.LoadMemberPort
import com.sh.user.member.domain.Member
import com.sh.user.member.domain.MemberId
import com.sh.user.member.domain.SaveMemberPort
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class MemberPersistenceAdapter(
        private val repository: MemberJpaRepository
) : SaveMemberPort, LoadMemberPort {

    override fun save(member: Member): Member {
        return repository.save(member)
    }

    override fun load(id: MemberId): Optional<Member> {
        return repository.findById(id)
    }
}