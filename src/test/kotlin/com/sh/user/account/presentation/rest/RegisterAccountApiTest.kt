package com.sh.user.account.presentation.rest

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.sh.user.account.domain.LoadAccountPort
import com.sh.user.cellphoneverification.presentation.rest.SendTokenRes
import com.sh.user.common.response.CommonResponse
import com.sh.user.config.AbstractIntegrationTest
import com.sh.user.cellphoneverification.presentation.rest.CellphoneVerificationTestSupporter
import com.sh.user.member.domain.LoadMemberPort
import com.sh.user.member.domain.MemberId
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class RegisterAccountApiTest : AbstractIntegrationTest() {

    @Autowired
    private lateinit var registerAccountTestSupporter: RegisterAccountTestSupporter
    @Autowired
    private lateinit var loadAccountPort: LoadAccountPort
    @Autowired
    private lateinit var loadMemberPort:LoadMemberPort


    @DisplayName("전화번호 인증후 회원가입을 한다.")
    @Test
    fun register_account_correctly() {
        val cellphone = "01012345678"
        val email = "abcdef@naver.com"
        val action = registerAccountTestSupporter.getRegisterAccountAction(cellphone, email, "1234!abcd")
        action.andDo(print())
                .andExpect { status().isOk }
                .andExpect {
                    val res = objectMapper.readValue(it.response.contentAsString, object: TypeReference<CommonResponse<Any>>(){})
                    assertEquals(CommonResponse.Result.SUCCESS, res.result)
                    val account = loadAccountPort.loadByLoginId(cellphone)
                    assertTrue(account.isPresent)
                    assertTrue(loadAccountPort.loadByLoginId(email).isPresent)
                    assertTrue(loadMemberPort.load(MemberId(account.get().memberId)).isPresent)
                }
    }

}

@Component
class RegisterAccountTestSupporter{
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var supporter: CellphoneVerificationTestSupporter

    fun getRegisterAccountAction(cellphone:String, email:String, rawPassword:String): ResultActions {
        val sendTokenAction = supporter.getSendTokenAction(cellphone)
        val sendTokenRes = objectMapper.readValue(
                sendTokenAction.andReturn().response.contentAsString, object: TypeReference<CommonResponse<SendTokenRes>>(){}
        ).data!!


        val req = RegisterAccountReq(
                email = email, cellphone = cellphone, verificationId = sendTokenRes.verificationId, verificationToken = sendTokenRes.verificationToken,
                rawPassword = rawPassword, name = "name", nickname = "nickname"
        )

        return mockMvc.perform(post(AccountApiUrl.REGISTER_ACCOUNT)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req))
        )
    }

}