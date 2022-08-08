package com.turbo.springboottestkotlin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class BankAccountRepositoryUnitTest {

    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var bankAccountRepository: BankAccountRepository

    private val bankAccount = BankAccount("TDBM", "123456789", "John Doe")

    @Test
    fun whenFindById_thenReturnBankAccount() {
        entityManager.persist(bankAccount)
        entityManager.flush()
        val ingBankAccountFound = bankAccountRepository.findByIdOrNull(bankAccount.id!!)
        assertThat(ingBankAccountFound == bankAccount)
    }

}