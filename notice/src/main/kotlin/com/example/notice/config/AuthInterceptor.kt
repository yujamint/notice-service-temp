package com.example.notice.config

import com.example.notice.repository.MemberRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.servlet.HandlerInterceptor

class AuthInterceptor(
    private val memberRepository: MemberRepository
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val session = request.getSession(true)
        if (session.getAttribute("JSESSIONID") != null) {
            return true
        }
        throw IllegalArgumentException("인증되지 않은 사용자입니다.")
    }
}