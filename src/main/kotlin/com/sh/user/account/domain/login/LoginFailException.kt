package com.sh.user.account.domain.login

import com.sh.user.common.exception.BaseException
import com.sh.user.common.exception.ErrorCode

class LoginFailException : BaseException(ErrorCode.ACCOUNT_LOGIN_FAIL, ErrorCode.ACCOUNT_LOGIN_FAIL.errorMsg) {
}