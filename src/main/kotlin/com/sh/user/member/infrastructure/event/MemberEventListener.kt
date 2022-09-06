package com.sh.user.member.infrastructure.event

import com.sh.user.common.id.IdGenerator
import com.sh.user.member.domain.RegisterMemberEvent
import com.sh.user.member.domain.SaveMemberPort
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class MemberEventListener(
        private val saveMemberPort: SaveMemberPort,
        private val idGenerator: IdGenerator,
        private val eventPublisher: ApplicationEventPublisher,
) {

    @EventListener
    fun handle(event: RegisterAccountEvent) {
        val member = saveMemberPort.save(event.toMember(idGenerator))
        eventPublisher.publishEvent(RegisterMemberEvent(
                memberId = member.id,
                email = member.email,
                cellphone = member.cellphone
        ))
    }
}