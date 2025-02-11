package br.com.soat.authenticationclient.adapters.inbound

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("private")
class PrivateController {

    @get:GetMapping
    val message: String
        get() = "Hello from private API controller"
}