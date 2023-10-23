package com.example.notice.controller.response

import com.example.notice.domain.Notice
import java.time.LocalDateTime

data class NoticesResponse(
    val id: Long,
    val title: String,
    val writer: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object{
        fun from(notice: Notice): NoticesResponse {
            return NoticesResponse(
                notice.id,
                notice.title,
                notice.member.name,
                notice.createdAt,
                notice.updatedAt
            )
        }
    }
}
