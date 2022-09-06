package com.sh.user.cellphoneverification.infrastructure

import com.sh.user.cellphoneverification.domain.CellphoneVerification
import com.sh.user.cellphoneverification.domain.LoadVerificationPort
import com.sh.user.cellphoneverification.domain.SaveVerificationPort
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class VerificationPersistenceAdapter(
        private val repository: CellphoneVerificationJpaRepository
) : SaveVerificationPort, LoadVerificationPort {
    override fun save(cv: CellphoneVerification): CellphoneVerification {
        return repository.save(cv)
    }

    override fun loadById(id: Long): Optional<CellphoneVerification> {
        return repository.findById(id)
    }
}