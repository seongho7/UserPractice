package com.sh.user.account.presentation.rest

import com.sh.user.account.application.AccountFacade
import com.sh.user.account.domain.login.CellphoneLoginCommand
import com.sh.user.common.response.CommonResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@RequestMapping(AccountApiUrl.LOGIN_CELLPHONE)
@RestController
class CellphoneLoginApi(
    private val accountFacade: AccountFacade
) {
    @PostMapping
    fun login(@Valid @RequestBody req:CellphoneLoginReq) : CommonResponse<LoginRes> {
        val token = accountFacade.login(req.toCommand())
        return CommonResponse.success(LoginRes(token))
    }
}

data class CellphoneLoginReq(
        @field:NotNull
        val verificationId:Long,
        @field:NotBlank
        val cellphone:String,
        @field:NotNull
        val verificationToken: String,
) {
    fun toCommand() = CellphoneLoginCommand(verificationId = verificationId, cellphone = cellphone, verificationToken = verificationToken)
}