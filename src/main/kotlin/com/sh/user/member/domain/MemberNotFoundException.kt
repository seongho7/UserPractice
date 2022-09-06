package com.sh.user.member.domain

import com.sh.user.common.exception.BaseException
import com.sh.user.common.exception.ErrorCode

class MemberNotFoundException(message:String) : BaseException(ErrorCode.COMMON_ENTITY_NOT_FOUND, message) {
}