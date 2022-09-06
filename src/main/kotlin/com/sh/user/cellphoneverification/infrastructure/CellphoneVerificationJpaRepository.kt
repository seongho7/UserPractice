package com.sh.user.cellphoneverification.infrastructure

import com.sh.user.cellphoneverification.domain.CellphoneVerification
import org.springframework.data.jpa.repository.JpaRepository

interface CellphoneVerificationJpaRepository : JpaRepository<CellphoneVerification, Long> {
}