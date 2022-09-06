package com.sh.user.cellphoneverification.presentation.rest

import com.sh.user.cellphoneverification.application.CellphoneVerificationFacade
import com.sh.user.cellphoneverification.domain.CellphoneVerification
import com.sh.user.cellphoneverification.domain.send.SendTokenCommand
import com.sh.user.common.response.CommonResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import javax.validation.Valid
import javax.validation.constraints.Pattern

@RequestMapping(CellphoneVerificationApiUrl.SEND_TOKEN)
@RestController
class SendVerificationTokenApi(
        private val facade:CellphoneVerificationFacade
) {
    @PostMapping
    fun sendToken(@Valid @RequestBody req:SendTokenReq) : CommonResponse<SendTokenRes> {
        val cv = facade.sendToken(req.toCommand())
        return CommonResponse.success(
                SendTokenRes(cv)
        )
    }
}

data class SendTokenReq(
        @field:Pattern(regexp = "^01([0|1|6|7|8|9])?([0-9]{3,4})?([0-9]{4})\$")
        val cellphone:String
) {
    fun toCommand() = SendTokenCommand(cellphone)
}

data class SendTokenRes(
        val verificationId:Long,
        val verificationToken: String,
        val expiredAt: LocalDateTime,
) {
    constructor(cv:CellphoneVerification) : this(
            verificationId = cv.id,
            verificationToken = cv.verificationToken,
            expiredAt = cv.expiredAt
    )
}