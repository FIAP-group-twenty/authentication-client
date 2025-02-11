package br.com.soat.authenticationclient.adapters.outbound

import br.com.soat.authenticationclient.core.domain.model.Customer
import br.com.soat.authenticationclient.ports.outbound.CustomerRepositoryPort
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class CustomerRepositoryAdapter(
    private val customerJpaRepository: CustomerJpaRepository
) : CustomerRepositoryPort {

    override fun findByUsername(username: String): Optional<Customer> {
        return customerJpaRepository.findByUsername(username)
    }

    override fun existsByUsername(username: String): Boolean {
        return customerJpaRepository.existsByUsername(username)
    }

    override fun save(customer: Customer): Customer {
        return customerJpaRepository.save(customer)
    }
}
