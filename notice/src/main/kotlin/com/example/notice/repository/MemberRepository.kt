package com.example.notice.repository

import com.example.notice.domain.Member
import org.springframework.data.repository.Repository

interface MemberRepository : Repository<Member, Long> {

    fun save(member: Member) : Member

    fun findMemberByName(name: String): Member?
}