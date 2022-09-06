package com.sh.user.cellphoneverification.presentation.rest

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.sh.user.common.response.CommonResponse
import com.sh.user.config.AbstractIntegrationTest
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class SendVerificationTokenApiTest : AbstractIntegrationTest() {

    @Autowired
    lateinit var supporter: CellphoneVerificationTestSupporter

    @DisplayName("휴대전화 검증 코드를 발송한다.")
    @Test
    fun send_verification_token_correctly() {
        val action = supporter.getSendTokenAction("01012341234")

        action.andDo(MockMvcResultHandlers.print())
                .andExpect { MockMvcResultMatchers.status().isOk }
                .andExpect {
                    val res = objectMapper.readValue(it.response.contentAsString, object: TypeReference<CommonResponse<SendTokenRes>>(){})
                    assertNotNull(res.data)
                    assertNotNull(res.data!!.verificationToken)
                    assertNotNull(res.data!!.expiredAt)
                    assertTrue(res.data!!.verificationId > 0)
                }
    }
}

@Component
class CellphoneVerificationTestSupporter {
    @Autowired
    protected lateinit var mockMvc: MockMvc

    @Autowired
    protected lateinit var objectMapper: ObjectMapper

        fun getSendTokenAction(cellphone:String): ResultActions {
            val req = SendTokenReq(cellphone = cellphone)

            return mockMvc.perform(post(CellphoneVerificationApiUrl.SEND_TOKEN)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(req))
            )
        }
}