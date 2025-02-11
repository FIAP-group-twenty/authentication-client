package br.com.soat.authenticationclient

import br.com.soat.authenticationclient.adapters.inbound.PrivateController
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PrivateControllerTest {

    private val controller = PrivateController()

    @Test
    fun `message should return hello message`() {
        // Act
        val message = controller.message

        // Assert
        assertEquals("Hello from private API controller", message)
    }
}