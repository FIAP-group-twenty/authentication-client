package br.com.soat.authenticationclient.adapters.outbound

import br.com.soat.authenticationclient.core.domain.model.Customer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CustomerJpaRepository : CrudRepository<Customer, String> {
    fun findByUsername(username: String): Optional<Customer>
    fun existsByUsername(username: String): Boolean
}
