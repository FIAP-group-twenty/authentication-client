package br.com.soat.authenticationclient

import br.com.soat.authenticationclient.adapters.outbound.UserDetailsAdapter
import br.com.soat.authenticationclient.core.domain.model.Customer
import br.com.soat.authenticationclient.ports.outbound.CustomerRepositoryPort
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.security.core.userdetails.UsernameNotFoundException
import java.util.*

class UserDetailsAdapterTest {

    private lateinit var customerRepository: CustomerRepositoryPort
    private lateinit var userDetailsAdapter: UserDetailsAdapter

    @BeforeEach
    fun setUp() {
        customerRepository = mock(CustomerRepositoryPort::class.java)
        userDetailsAdapter = UserDetailsAdapter(customerRepository)
    }

    @Test
    fun `loadUserByUsername should return UserDetails`() {
        // Arrange
        val username = "user"
        val customer = Customer(username, "pass", "123456789")
        `when`(customerRepository.findByUsername(username)).thenReturn(Optional.of(customer))

        // Act
        val userDetails = userDetailsAdapter.loadUserByUsername(username)

        // Assert
        assertEquals(username, userDetails.username)
        verify(customerRepository, times(1)).findByUsername(username)
    }

    @Test
    fun `loadUserByUsername should throw UsernameNotFoundException`() {
        // Arrange
        val username = "user"
        `when`(customerRepository.findByUsername(username)).thenReturn(Optional.empty())

        // Act & Assert
        assertThrows(UsernameNotFoundException::class.java) {
            userDetailsAdapter.loadUserByUsername(username)
        }
        verify(customerRepository, times(1)).findByUsername(username)
    }
}