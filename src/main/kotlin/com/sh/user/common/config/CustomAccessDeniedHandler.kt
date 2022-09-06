package com.sh.user.common.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.sh.user.common.exception.ErrorCode
import com.sh.user.common.response.CommonResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAccessDeniedHandler(
        private val objectMapper: ObjectMapper
) : AccessDeniedHandler {
    override fun handle(request: HttpServletRequest,
                        response: HttpServletResponse,
                        accessDeniedException: AccessDeniedException) {
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpStatus.FORBIDDEN.value()

        val commonResponse = CommonResponse.fail(ErrorCode.ACCESS_DENIED)

        response.writer.print(objectMapper.writeValueAsString(commonResponse))
        response.writer.flush()
        response.writer.close()
    }
}