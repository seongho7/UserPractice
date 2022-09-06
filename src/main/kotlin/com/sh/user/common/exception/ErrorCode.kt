package com.sh.user.common.exception

enum class ErrorCode(val errorMsg:String) {

    COMMON_SYSTEM_ERROR("일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요."), // 장애 상황
    COMMON_INVALID_PARAMETER("요청한 값이 올바르지 않습니다."),
    COMMON_ENTITY_NOT_FOUND("존재하지 않는 엔티티입니다."),
    COMMON_ILLEGAL_STATUS("잘못된 상태값입니다."),

    ACCOUNT_EMAIL_ALREADY_EXIST("이미 존재하는 이메일입니다."),
    ACCOUNT_CELLPHONE_ALREADY_EXIST("이미 존재하는 휴대전화번호입니다."),
    ACCOUNT_NOT_FOUND("계정이 존재하지 않습니다."),

    ACCOUNT_LOGIN_FAIL("로그인에 실패했습니다."),
    ACCOUNT_TOKEN_EXPIRED("토큰이 만료됬습니다."),
    ACCOUNT_TOKEN_INVALID("인증토큰이 유효하지 않습니다."),

    ACCESS_DENIED("권한이 없습니다."),


}