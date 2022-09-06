package com.sh.user.account.domain

import com.sh.user.common.exception.BaseException
import com.sh.user.common.exception.ErrorCode

class AccountNotFoundException(
        message:String = ErrorCode.ACCOUNT_NOT_FOUND.errorMsg
) : BaseException(errorCode = ErrorCode.ACCOUNT_NOT_FOUND, message = message) {
}