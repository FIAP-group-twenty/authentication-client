package br.com.soat.authenticationclient.ports.outbound

import br.com.soat.authenticationclient.core.domain.model.Customer
import java.util.*

interface CustomerRepositoryPort {
    fun findByUsername(username: String): Optional<Customer>
    fun existsByUsername(username: String): Boolean
    fun save(customer: Customer): Customer
}
