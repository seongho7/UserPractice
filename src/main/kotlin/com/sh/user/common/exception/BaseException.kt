package com.sh.user.common.exception

open class BaseException(val errorCode: ErrorCode, message: String) : RuntimeException(message) {
}
