package br.com.soat.authenticationclient.ports.outbound

import org.springframework.security.core.userdetails.UserDetails

interface UserDetailsPort {
    fun loadUserByUsername(username: String): UserDetails
}
