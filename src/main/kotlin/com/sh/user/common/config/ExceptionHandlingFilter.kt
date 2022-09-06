package com.sh.user.common.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.sh.user.common.exception.ErrorCode
import com.sh.user.common.response.CommonResponse
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ExceptionHandlingFilter(
        private val objectMapper: ObjectMapper
) : OncePerRequestFilter(){
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

        try {
            filterChain.doFilter(request, response)
        } catch (e:ExpiredJwtException) {
            response(response, ErrorCode.ACCOUNT_TOKEN_EXPIRED)
        } catch (e:JwtException) {
            response(response, ErrorCode.ACCOUNT_TOKEN_INVALID)
        } catch (e:Exception) {
            response(response, ErrorCode.COMMON_SYSTEM_ERROR)
        }

    }

    private fun response(response: HttpServletResponse, errorCode: ErrorCode) {
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpStatus.UNAUTHORIZED.value()

        val commonResponse = CommonResponse.fail(errorCode)

        response.writer.print(objectMapper.writeValueAsString(commonResponse))
        response.writer.flush()
        response.writer.close()
    }
}