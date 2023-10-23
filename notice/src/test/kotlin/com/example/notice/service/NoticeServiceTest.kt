package com.example.notice.service

import com.example.notice.domain.Member
import com.example.notice.fake.repository.FakeMemberRepository
import com.example.notice.fake.repository.FakeNoticeRepository
import com.example.notice.service.dto.NoticeSaveRequest
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class NoticeServiceTest {
    private val noticeRepository = FakeNoticeRepository()
    private val memberRepository = FakeMemberRepository()
    private val slackService = mockk<SlackService>(name = "slackService", relaxed = true)
    private val noticeService: NoticeService = NoticeService(noticeRepository, memberRepository, slackService)

    @BeforeEach
    fun setUp() {
        every { slackService.sendMessageByUser(any(), any()) } returns Unit
    }

    @Test
    fun `공지사항을 저장한다`() {
        //given
        val member = Member("파워")
        memberRepository.save(member)

        //when
        val request = NoticeSaveRequest("파워", "공지", "내용", "공지 채널")
        val announcementId = noticeService.save(request)

        //then
        Assertions.assertThat(announcementId).isNotEqualTo(0)
    }

    @Test
    fun `멤버가 유효하지 않은경우 예외가 발생한다`() {
        //given
        val request = NoticeSaveRequest("박스터", "공지", "내용", "공지 채널")

        //then
        assertThrows<IllegalArgumentException> { noticeService.save(request) }
    }

}
