package com.turbo.springboottestkotlin

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

@SpringBootTest(
    classes = [KotlinTestingDemoApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class KotlinTestingDemoApplicationIntegrationTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun whePostCalled_thenShouldReturnBankObject() {
        val result = restTemplate.postForEntity(
            "/api/bankAccount",
            BankAccount("TDBM", "123456789", "John Doe"),
            BankAccount::class.java
        )

        Assertions.assertNotNull(result)
        Assertions.assertEquals(HttpStatus.OK, result?.statusCode)
        Assertions.assertEquals("TDBM", result.body?.bankCode)
    }

    @Test
    fun whenGetCalled_thenShouldBadReqeust() {
        val result = restTemplate.getForEntity("/api/bankAccount?id=2", BankAccount::class.java)

        Assertions.assertNotNull(result)
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result?.statusCode)
    }
}
