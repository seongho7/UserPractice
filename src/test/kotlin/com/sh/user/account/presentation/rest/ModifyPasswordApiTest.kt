package com.sh.user.account.presentation.rest

import com.fasterxml.jackson.core.type.TypeReference
import com.sh.user.accesstoken.domain.register.AccessTokenProvider
import com.sh.user.account.domain.LoadAccountPort
import com.sh.user.common.response.CommonResponse
import com.sh.user.config.AbstractIntegrationTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ModifyPasswordApiTest : AbstractIntegrationTest() {
    @Autowired
    private lateinit var registerAccountTestSupporter: RegisterAccountTestSupporter
    @Autowired
    private lateinit var tokenProvider: AccessTokenProvider
    @Autowired
    private lateinit var loadAccountPort: LoadAccountPort

    @Test
    fun modify_password_correctly() {
        val cellphone = "01012341234"
        val email = "abcd@naver.com"
        val rawPassword = "abcd!1234"
        registerAccountTestSupporter.getRegisterAccountAction(cellphone, email, rawPassword)
        val account = loadAccountPort.loadByLoginId(email)
        val accessToken = tokenProvider.createToken(account.get().id.id, 600000L)

        val req = ModifyPasswordReq(rawPassword)
        val action  = mockMvc.perform(MockMvcRequestBuilders.post(AccountApiUrl.MODIFY_PASSWORD)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer $accessToken")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
        )
        action.andDo(print())
                .andExpect { status().isOk }
                .andExpect {
                    val res = objectMapper.readValue(it.response.contentAsString, object: TypeReference<CommonResponse<Any>>(){})
                    assertEquals(CommonResponse.Result.SUCCESS, res.result)
                }
    }
}