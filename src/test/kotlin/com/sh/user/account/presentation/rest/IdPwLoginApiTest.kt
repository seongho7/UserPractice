package com.sh.user.account.presentation.rest

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.sh.user.common.response.CommonResponse
import com.sh.user.config.AbstractIntegrationTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.util.StringUtils
import java.time.LocalDateTime

class IdPwLoginApiTest : AbstractIntegrationTest() {
    @Autowired
    private lateinit var idPwLoginApiTestSupporter: IdPwLoginApiTestSupporter

    @DisplayName("이메일, 비밀번호를 입력하여 로그인한다.")
    @Test
    fun login_by_email_correctly() {
        val cellphone = "01012341345"
        val email = "abcdefg@naver.com"
        val rawPassword = "1234!abcd"
        val action = idPwLoginApiTestSupporter.getLoginAction(cellphone, email, rawPassword, email)
        action.andDo(print())
                .andExpect { status().isOk }
                .andExpect {
                    val res = objectMapper.readValue(it.response.contentAsString, object: TypeReference<CommonResponse<LoginRes>>(){}).data
                    assertNotNull(res)
                    if(res != null) {
                        assertTrue(StringUtils.hasText(res.accessToken))
                        assertTrue(res.accessTokenExpiredAt.isAfter(LocalDateTime.now()))
                        assertTrue(StringUtils.hasText(res.refreshToken))
                        assertTrue(res.refreshTokenExpiredAt.isAfter(LocalDateTime.now()))
                    }
                }
    }
}

@Component
class IdPwLoginApiTestSupporter {
    @Autowired
    protected lateinit var mockMvc: MockMvc
    @Autowired
    protected lateinit var objectMapper: ObjectMapper
    @Autowired
    protected lateinit var registerAccountTestSupporter: RegisterAccountTestSupporter

    fun getLoginAction(cellphone:String, email:String, rawPassword:String, loginId:String): ResultActions {
        registerAccountTestSupporter.getRegisterAccountAction(cellphone, email, rawPassword)

        val req = IdPwLoginReq(loginId, rawPassword)

        return mockMvc.perform(post(AccountApiUrl.LOGIN_ID_PW)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
    }

}