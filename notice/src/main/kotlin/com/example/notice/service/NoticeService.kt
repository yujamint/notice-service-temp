package com.example.notice.service

import com.example.notice.domain.Notice
import com.example.notice.repository.MemberRepository
import com.example.notice.repository.NoticeRepository
import com.example.notice.service.dto.NoticeSaveRequest
import org.springframework.stereotype.Service

@Service
class NoticeService(
    private val noticeRepository: NoticeRepository,
    private val memberRepository: MemberRepository,
    private val slackService: SlackService
) {

    fun save(request: NoticeSaveRequest): Long {
        val member = memberRepository.findMemberByName(request.writer)
            ?: throw IllegalArgumentException("회원이 없습니다.")
        val notice = Notice(request.title, request.content, member)
        slackService.sendMessageByUser(request.channelId, request.content)
        return noticeRepository.save(notice).id
    }

    fun findById(noticeId: Long, memberId: Long?): Notice {
        memberId ?: throw IllegalArgumentException("로그인한 사용자가 아닙니다.")
        return noticeRepository.findById(noticeId) ?: throw IllegalArgumentException("공지사항이 존재하지 않습니다.")
    }

    fun findAll(): List<Notice> {
        return noticeRepository.findAll()
    }
}
