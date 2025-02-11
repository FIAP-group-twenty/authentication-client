package br.com.soat.authenticationclient

import br.com.soat.authenticationclient.adapters.outbound.CustomerRepositoryAdapter
import br.com.soat.authenticationclient.adapters.outbound.JwtServiceAdapter
import br.com.soat.authenticationclient.core.application.service.AuthenticationService
import br.com.soat.authenticationclient.core.domain.model.Customer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.security.crypto.password.PasswordEncoder

class AuthenticationServiceTest {

    private lateinit var jwtService: JwtServiceAdapter
    private lateinit var customerRepository: CustomerRepositoryAdapter
    private lateinit var passwordEncoder: PasswordEncoder
    private lateinit var authenticationService: AuthenticationService

    @BeforeEach
    fun setUp() {
        jwtService = mock(JwtServiceAdapter::class.java)
        customerRepository = mock(CustomerRepositoryAdapter::class.java)
        passwordEncoder = mock(PasswordEncoder::class.java)
        authenticationService = AuthenticationService(jwtService, customerRepository, passwordEncoder)
    }

    @Test
    fun `authenticate should return token`() {
        // Arrange
        val username = "user"
        val password = "pass"
        val token = "generated-token"
        `when`(jwtService.generateToken(username, password)).thenReturn(token)

        // Act
        val result = authenticationService.authenticate(username, password)

        // Assert
        assertEquals(token, result)
        verify(jwtService, times(1)).generateToken(username, password)
    }

    @Test
    fun `register should save customer`() {
        // Arrange
        val customer = Customer("user", "pass", "123456789")
        val encodedPassword = "encodedPass"
        val customerWithEncodedPassword = customer.copy(password = encodedPassword)

        // Configuração dos mocks
        `when`(customerRepository.existsByUsername(customer.username)).thenReturn(false)
        `when`(passwordEncoder.encode(customer.password)).thenReturn(encodedPassword)
        `when`(customerRepository.save(customerWithEncodedPassword)).thenReturn(customerWithEncodedPassword)

        // Act
        val result = authenticationService.register(customer)

        // Assert
        assertEquals(customerWithEncodedPassword, result) // Verifica se o resultado é o esperado
        verify(customerRepository, times(1)).existsByUsername(customer.username)
        verify(passwordEncoder, times(1)).encode(customer.password)
        verify(customerRepository, times(1)).save(customerWithEncodedPassword)
    }
}