package br.com.soat.authenticationclient.adapters.outbound

import br.com.soat.authenticationclient.ports.outbound.CustomerRepositoryPort
import br.com.soat.authenticationclient.ports.outbound.TokenProviderPort
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class JwtServiceAdapter(
    private val encoder: JwtEncoder,
    private val customerRepository: CustomerRepositoryPort,
    private val passwordEncoder: PasswordEncoder
) : TokenProviderPort {

    override fun generateToken(username: String, password: String): String {
        val customer = customerRepository.findByUsername(username)
            .orElseThrow { IllegalArgumentException("User not found") }

        // Verifica se a senha está correta
        if (!passwordEncoder.matches(password, customer.password)) {
            throw IllegalArgumentException("Invalid credentials")
        }

        val now = Instant.now()
        val expire = 36000L  // Token expira em 10 horas

        val claims = JwtClaimsSet.builder()
            .issuer("token-provider")
            .issuedAt(now)
            .expiresAt(now.plusSeconds(expire))
            .subject(username)
            .claim("cellphone", customer.cellphone)
            .build()

        return encoder.encode(JwtEncoderParameters.from(claims)).tokenValue
    }
}
