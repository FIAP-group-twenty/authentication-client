package br.com.soat.authenticationclient.core.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "tb_customer")
data class Customer(
    @Id
    @Column(name = "username", nullable = false)
    val username: String,
    val password: String,
    val cellphone: String
)