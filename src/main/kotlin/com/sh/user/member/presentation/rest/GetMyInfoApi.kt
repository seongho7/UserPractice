package com.sh.user.member.presentation.rest

import com.sh.user.account.application.AccountFacade
import com.sh.user.account.domain.AccountId
import com.sh.user.common.response.CommonResponse
import com.sh.user.member.application.MemberFacade
import com.sh.user.member.domain.Member
import com.sh.user.member.domain.MemberId
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@PreAuthorize("isAuthenticated()")
@RequestMapping(MemberApiUrl.GET_MY_INFO)
@RestController
class GetMyInfoApi(
    private val memberFacade: MemberFacade,
    private val accountFacade: AccountFacade,
) {
    @GetMapping
    fun get(@AuthenticationPrincipal accountId: AccountId) : CommonResponse<GetMyInfoRes> {
        val account = accountFacade.load(accountId)
        val member = memberFacade.load(MemberId(account.memberId))

        return CommonResponse.success(GetMyInfoRes(member))
    }
}

data class GetMyInfoRes(
        val memberId:Long,
        val email:String,
        val nickname:String,
        val name:String,
        val cellphone:String,
        val createdAt: LocalDateTime,
) {
    constructor(m:Member) : this(
        memberId = m.id.id,
        email = m.email,
        nickname = m.nickname,
        name = m.name,
        cellphone = m.cellphone,
        createdAt = m.createdAt,
    )
}
