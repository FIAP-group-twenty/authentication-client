package br.com.soat.authenticationclient.adapters.inbound

import br.com.soat.authenticationclient.core.domain.model.Customer
import br.com.soat.authenticationclient.ports.inbound.AuthenticationUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationController(
    private val useCase: AuthenticationUseCase
) {

    @PostMapping("/authenticate")
    fun authenticate(@RequestHeader username: String, @RequestHeader password: String): ResponseEntity<String> {
        return ResponseEntity.ok(useCase.authenticate(username, password))
    }

    @PostMapping("/register")
    fun register(
        @RequestBody customer: Customer
    ): ResponseEntity<Customer> {
        return ResponseEntity.status(HttpStatus.CREATED).body(useCase.register(customer))
    }
}