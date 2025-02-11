package br.com.soat.authenticationclient.core.application.service

import br.com.soat.authenticationclient.adapters.outbound.CustomerRepositoryAdapter
import br.com.soat.authenticationclient.adapters.outbound.JwtServiceAdapter
import br.com.soat.authenticationclient.core.domain.model.Customer
import br.com.soat.authenticationclient.ports.inbound.AuthenticationUseCase
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class AuthenticationService(
    private val jwtService: JwtServiceAdapter,
    private val customerRepository: CustomerRepositoryAdapter,
    private val passwordEncoder: PasswordEncoder
): AuthenticationUseCase {

    override fun authenticate(username: String, password: String): String = jwtService.generateToken(username, password)

    override fun register(customer: Customer): Customer {
        if (customerRepository.existsByUsername(customer.username)) {
            throw IllegalArgumentException("Usuário já existe")
        }
        return customerRepository.save(
            Customer(
                username = customer.username,
                cellphone = customer.cellphone,
                password = passwordEncoder.encode(customer.password)
            )
        )
    }
}