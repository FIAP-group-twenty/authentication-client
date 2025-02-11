package br.com.soat.authenticationclient.ports.inbound

import br.com.soat.authenticationclient.core.domain.model.Customer

interface AuthenticationUseCase {
    fun authenticate(username: String, password: String): String

    fun register(customer: Customer): Customer
}
