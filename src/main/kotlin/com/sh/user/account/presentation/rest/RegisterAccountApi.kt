package com.sh.user.account.presentation.rest

import com.sh.user.account.application.AccountFacade
import com.sh.user.account.domain.register.RegisterAccountCommand
import com.sh.user.common.response.CommonResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@RequestMapping(AccountApiUrl.REGISTER_ACCOUNT)
@RestController
class RegisterAccountApi(
        private val accountFacade: AccountFacade
) {
    @PostMapping
    fun register(@Valid @RequestBody req:RegisterAccountReq) : CommonResponse<Any> {
        accountFacade.registerAccount(req.toCommand())
        return CommonResponse.success()
    }
}

data class RegisterAccountReq(
        @field:Email
        val email:String,
        @field:Pattern(regexp = "^01([0|1|6|7|8|9])?([0-9]{3,4})?([0-9]{4})\$")
        val cellphone:String,
        @field:NotNull
        val verificationId:Long,
        @field:NotBlank
        val verificationToken:String,
        @field:Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[@$!%*#?&])[A-Za-z[0-9]@$!%*#?&]{8,}$",
                message = "패스워드는 최소 8자, 영문자, 숫자를 포함해야합니다.")
        val rawPassword:String,
        @field:NotBlank
        @field:Size(max = 32)
        val name:String,
        @field:NotBlank
        @field:Size(max = 32)
        val nickname:String,
) {
    fun toCommand() : RegisterAccountCommand {
        return RegisterAccountCommand(
                email = email,
                cellphone = cellphone,
                verificationId = verificationId,
                verificationToken = verificationToken,
                rawPassword = rawPassword,
                name = name,
                nickname = nickname
        )
    }
}