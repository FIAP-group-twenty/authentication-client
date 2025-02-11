package br.com.soat.authenticationclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AuthenticationClientApplication

fun main(args: Array<String>) {
    runApplication<AuthenticationClientApplication>(*args)
}
