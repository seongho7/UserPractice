package com.sh.user.account.presentation.rest

import com.sh.user.account.application.AccountFacade
import com.sh.user.account.domain.login.IdPwLoginCommand
import com.sh.user.common.response.CommonResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@RequestMapping(AccountApiUrl.LOGIN_ID_PW)
@RestController
class IdPwLoginApi(
        private val accountFacade: AccountFacade
) {

    @PostMapping
    fun login(@Valid @RequestBody req:IdPwLoginReq) : CommonResponse<LoginRes> {
        val accessToken = accountFacade.login(req.toCommand())
        return CommonResponse.success(LoginRes(accessToken))
    }
}

data class IdPwLoginReq(
        @NotBlank
        val loginId:String,
        @NotBlank
        val rawPassword:String
) {
    fun toCommand() = IdPwLoginCommand(loginId = loginId, rawPassword = rawPassword)
}