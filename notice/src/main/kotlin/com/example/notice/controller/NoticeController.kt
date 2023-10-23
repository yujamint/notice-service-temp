package com.example.notice.controller

import com.example.notice.controller.response.NoticeResponse
import com.example.notice.controller.response.NoticesResponse
import com.example.notice.service.NoticeService
import com.example.notice.service.dto.NoticeSaveRequest
import jakarta.servlet.http.HttpSession
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class NoticeController(
    private val noticeService: NoticeService
) {

    @PostMapping("/notices")
    fun save(@RequestBody noticeSaveRequest: NoticeSaveRequest): ResponseEntity<Void> {
        val noticeId = noticeService.save(noticeSaveRequest)
        return ResponseEntity.created(URI.create("/notices/${noticeId}"))
            .build()
    }

    @GetMapping("/notices/{noticeId}")
    fun findNotice(@PathVariable noticeId: Long, httpSession: HttpSession): ResponseEntity<NoticeResponse> {
        val memberId = httpSession.getAttribute("memberId")

        if (memberId is Long) {
            val notice = noticeService.findById(noticeId, memberId)
            return ResponseEntity.ok(NoticeResponse.from(notice))
        }
        throw IllegalArgumentException("로그인한 사용자가 아닙니다.")
    }

    @GetMapping("/notices")
    fun findAll(): ResponseEntity<List<NoticesResponse>> {
        val notice = noticeService.findAll()
        return ResponseEntity.ok(notice.map { NoticesResponse.from(it) })
    }
}
