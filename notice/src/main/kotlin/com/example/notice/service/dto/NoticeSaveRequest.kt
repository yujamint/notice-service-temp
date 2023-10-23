package com.example.notice.service.dto

data class NoticeSaveRequest(
    val writer: String,
    val title: String,
    val content: String,
    val channelId: String
) {
}
