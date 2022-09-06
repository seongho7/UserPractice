package com.sh.user.common.exception

class IllegalStatusException(message:String = ErrorCode.COMMON_ILLEGAL_STATUS.errorMsg)
    : BaseException(ErrorCode.COMMON_ILLEGAL_STATUS, message) {
}