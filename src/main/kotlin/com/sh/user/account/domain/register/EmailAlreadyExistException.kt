package com.sh.user.account.domain.register

import com.sh.user.common.exception.BaseException
import com.sh.user.common.exception.ErrorCode

class EmailAlreadyExistException(message:String = ErrorCode.ACCOUNT_EMAIL_ALREADY_EXIST.errorMsg)
    : BaseException(ErrorCode.ACCOUNT_EMAIL_ALREADY_EXIST, message) {
}