package com.sh.user.account.domain.register

import com.sh.user.common.exception.BaseException
import com.sh.user.common.exception.ErrorCode

class CellphoneAlreadyExistException(message:String = ErrorCode.ACCOUNT_CELLPHONE_ALREADY_EXIST.errorMsg)
    : BaseException(ErrorCode.ACCOUNT_CELLPHONE_ALREADY_EXIST, message) {
}