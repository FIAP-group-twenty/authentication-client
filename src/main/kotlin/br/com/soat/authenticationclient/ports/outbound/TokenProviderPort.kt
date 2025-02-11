package br.com.soat.authenticationclient.ports.outbound

interface TokenProviderPort {
    fun generateToken(username: String, password: String): String
}
