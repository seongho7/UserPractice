package com.sh.user.account.presentation.rest

import com.fasterxml.jackson.core.type.TypeReference
import com.sh.user.cellphoneverification.presentation.rest.CellphoneVerificationTestSupporter
import com.sh.user.cellphoneverification.presentation.rest.SendTokenRes
import com.sh.user.common.response.CommonResponse
import com.sh.user.config.AbstractIntegrationTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.util.StringUtils
import java.time.LocalDateTime

class CellphoneLoginApiTest : AbstractIntegrationTest() {

    @Autowired
    private lateinit var registerAccountTestSupporter: RegisterAccountTestSupporter
    @Autowired
    private lateinit var supporter: CellphoneVerificationTestSupporter

    @DisplayName("휴대폰으로 검증코드를 발송하여 로그인한다.")
    @Test
    fun login_cellphone_correctly() {
        val cellphone = "01012341234"
        val email = "abcd@naver.com"
        val rawPassword = "abcd!1234"
        registerAccountTestSupporter.getRegisterAccountAction(cellphone, email, rawPassword)

        val sendTokenAction = supporter.getSendTokenAction(cellphone)
        val sendTokenRes = objectMapper.readValue(
                sendTokenAction.andReturn().response.contentAsString, object: TypeReference<CommonResponse<SendTokenRes>>(){}
        ).data!!

        val req = CellphoneLoginReq(verificationId = sendTokenRes.verificationId, cellphone = cellphone, verificationToken = sendTokenRes.verificationToken)

        val action  = mockMvc.perform(post(AccountApiUrl.LOGIN_CELLPHONE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
        )
        action.andDo(print())
                .andExpect { status().isOk }
                .andExpect {
                    val res = objectMapper.readValue(it.response.contentAsString, object: TypeReference<CommonResponse<LoginRes>>(){}).data
                    Assertions.assertNotNull(res)
                    if(res != null) {
                        Assertions.assertTrue(StringUtils.hasText(res.accessToken))
                        Assertions.assertTrue(res.accessTokenExpiredAt.isAfter(LocalDateTime.now()))
                        Assertions.assertTrue(StringUtils.hasText(res.refreshToken))
                        Assertions.assertTrue(res.refreshTokenExpiredAt.isAfter(LocalDateTime.now()))
                    }
                }

    }
}