package com.sh.user.translator

import com.sh.user.account.domain.register.RegisterAccountEvent
import com.sh.user.member.domain.RegisterMemberEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class DomainEventTranslator(
        private val eventPublisher: ApplicationEventPublisher
) {
    @EventListener
    fun translate(event:RegisterAccountEvent) {
        eventPublisher.publishEvent(com.sh.user.member.infrastructure.event.RegisterAccountEvent(
                email = event.email,
                cellphone = event.cellphone,
                name = event.name,
                nickname = event.nickname
        ))
    }

    @EventListener
    fun translate(event:RegisterMemberEvent) {
        eventPublisher.publishEvent(
                com.sh.user.account.infrastructure.event.RegisterMemberEvent(
                        memberId = event.memberId.id,
                        email = event.email,
                        cellphone = event.cellphone,
                )
        )
    }
}