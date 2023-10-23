package com.example.notice.fake.repository

import com.example.notice.domain.Notice
import com.example.notice.repository.NoticeRepository

class FakeNoticeRepository : NoticeRepository {

    val map = HashMap<Long, Notice>()
    var id = 0L

    override fun save(notice: Notice): Notice {
        id++
        val savedNotice = Notice(notice.title, notice.content, notice.member, id)
        map.put(id, savedNotice)
        return savedNotice
    }

    override fun findById(noticeId: Long): Notice? {
        throw UnsupportedOperationException("not supported")
    }

    override fun findAll(): List<Notice> {
        throw UnsupportedOperationException("not supported")
    }
}
