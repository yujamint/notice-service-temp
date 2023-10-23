package com.example.notice.config

import jakarta.servlet.http.HttpSession
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class MemberArgumentResolver(
    private val httpSession: HttpSession
) : HandlerMethodArgumentResolver {

    override fun supportsParameter(
        parameter: MethodParameter
    ): Boolean {
        return parameter.hasParameterAnnotation(Authorization::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        return httpSession.getAttribute("JSESSIONID")
    }
}