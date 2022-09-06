package com.sh.user.member.presentation.rest

import com.fasterxml.jackson.core.type.TypeReference
import com.sh.user.accesstoken.domain.register.AccessTokenProvider
import com.sh.user.account.domain.LoadAccountPort
import com.sh.user.account.presentation.rest.RegisterAccountTestSupporter
import com.sh.user.common.response.CommonResponse
import com.sh.user.config.AbstractIntegrationTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class GetMyInfoApiTest : AbstractIntegrationTest() {
    @Autowired
    private lateinit var registerAccountTestSupporter: RegisterAccountTestSupporter
    @Autowired
    private lateinit var tokenProvider: AccessTokenProvider
    @Autowired
    private lateinit var loadAccountPort: LoadAccountPort

    @Test
    fun get_my_info_correctly() {
        val cellphone = "01012341234"
        val email = "abcd@naver.com"
        val rawPassword = "abcd!1234"
        registerAccountTestSupporter.getRegisterAccountAction(cellphone, email, rawPassword)
        val account = loadAccountPort.loadByLoginId(email)
        val accessToken = tokenProvider.createToken(account.get().id.id, 600000L)

        val action  = mockMvc.perform(get(MemberApiUrl.GET_MY_INFO)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer $accessToken")
                .contentType(MediaType.APPLICATION_JSON)
        )
        action.andDo(MockMvcResultHandlers.print())
                .andExpect { MockMvcResultMatchers.status().isOk }
                .andExpect {
                    val res = objectMapper.readValue(it.response.contentAsString, object: TypeReference<CommonResponse<GetMyInfoRes>>(){})
                    assertEquals(CommonResponse.Result.SUCCESS, res.result)
                    assertEquals(cellphone, res.data?.cellphone)
                    assertEquals(email, res.data?.email)
                }
    }
}