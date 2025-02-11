package br.com.soat.authenticationclient.adapters.outbound


import br.com.soat.authenticationclient.core.domain.model.Customer
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserAuthenticated(private val customer: Customer) : UserDetails {

    override fun getUsername(): String = customer.username

    override fun getPassword(): String = customer.password

    override fun getAuthorities(): Collection<GrantedAuthority> = listOf(GrantedAuthority { "USER" })

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
