package com.turbo.springboottestkotlin

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull

class BankAccountServiceUnitTest {

    private val bankAccountRepository: BankAccountRepository = mockk()
    private val bankAccountService = BankAccountService(bankAccountRepository)

    private val bankAccount = BankAccount("TDBM", "123456789", "John Doe")

    @Test
    fun whenGetBankAccount_thenReturnBankAccount() {
        //given
        every { bankAccountRepository.findByIdOrNull(1) } returns bankAccount

        //when
        val result = bankAccountService.getBankAccount(1)

        //then
        verify(exactly = 1) { bankAccountRepository.findByIdOrNull(1) }
        Assertions.assertEquals(bankAccount, result)
    }
}