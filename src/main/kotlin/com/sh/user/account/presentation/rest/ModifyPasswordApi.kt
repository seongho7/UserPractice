package com.sh.user.account.presentation.rest

import com.sh.user.account.application.AccountFacade
import com.sh.user.account.domain.AccountId
import com.sh.user.account.domain.modify.ModifyPasswordCommand
import com.sh.user.common.response.CommonResponse
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Pattern

@PreAuthorize("isAuthenticated()")
@RequestMapping(AccountApiUrl.MODIFY_PASSWORD)
@RestController
class ModifyPasswordApi(
    private val accountFacade: AccountFacade
) {
    @PostMapping
    fun modify(@AuthenticationPrincipal accountId: AccountId,
               @Valid @RequestBody req:ModifyPasswordReq) : CommonResponse<Any> {

        accountFacade.modifyPassword(ModifyPasswordCommand(accountId, req.rawPassword))

        return CommonResponse.success()
    }

}

data class ModifyPasswordReq(
        @field:Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[@$!%*#?&])[A-Za-z[0-9]@$!%*#?&]{8,}$",
                message = "패스워드는 최소 8자, 영문자, 숫자를 포함해야합니다.")
        val rawPassword:String,
)