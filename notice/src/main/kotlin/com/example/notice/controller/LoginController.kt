package com.example.notice.controller

import com.example.notice.controller.request.LoginRequest
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController {

    @GetMapping("/login")
    fun login(
        @RequestBody request: LoginRequest,
        httpRequest: HttpServletRequest
    ): ResponseEntity<Void> {
        val validPassword = "1234"

        if (request.password.equals(validPassword)) {
            val session = httpRequest.getSession(true)
            session.setAttribute("JSESSIONID", request.userName)
            return ResponseEntity.ok().build()
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
    }
}