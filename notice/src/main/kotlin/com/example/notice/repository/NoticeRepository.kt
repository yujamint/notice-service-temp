package com.example.notice.repository

import com.example.notice.domain.Notice
import org.springframework.data.repository.Repository

interface NoticeRepository : Repository<Notice, Long> {

    fun save(notice: Notice): Notice

    fun findById(noticeId: Long): Notice?

    fun findAll(): List<Notice>
}
