package com.sh.user.cellphoneverification.domain.verify

import com.sh.user.common.exception.BaseException
import com.sh.user.common.exception.ErrorCode

class InvalidTokenException : BaseException(ErrorCode.ACCOUNT_TOKEN_INVALID, ErrorCode.ACCOUNT_TOKEN_INVALID.errorMsg) {
}