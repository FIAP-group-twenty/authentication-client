package br.com.soat.authenticationclient.adapters.outbound

import br.com.soat.authenticationclient.ports.outbound.CustomerRepositoryPort
import br.com.soat.authenticationclient.ports.outbound.UserDetailsPort
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class UserDetailsAdapter(
    private val customerRepository: CustomerRepositoryPort
) : UserDetailsPort {

    override fun loadUserByUsername(username: String): UserDetails {
        val customer = customerRepository.findByUsername(username)
            .orElseThrow { UsernameNotFoundException("User Not Found with username: $username") }

        return UserAuthenticated(customer)
    }
}
