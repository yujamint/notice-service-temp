package com.example.notice.controller.response

import com.example.notice.domain.Notice
import java.time.LocalDateTime

data class NoticeResponse(
    val id: Long,
    val title: String,
    val content: String,
    val writer: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object{
        fun from(notice: Notice): NoticeResponse {
            return NoticeResponse(
                notice.id,
                notice.title,
                notice.content,
                notice.member.name,
                notice.createdAt,
                notice.updatedAt
            )
        }
    }
}
