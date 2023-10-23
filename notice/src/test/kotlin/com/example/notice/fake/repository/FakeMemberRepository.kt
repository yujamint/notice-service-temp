package com.example.notice.fake.repository

import com.example.notice.domain.Member
import com.example.notice.repository.MemberRepository

class FakeMemberRepository : MemberRepository {

    private val map = HashMap<Long, Member>()
    private var id = 0L

    override fun save(member: Member): Member {
        id++
        val savedMember = Member(member.name, id)
        map.put(id, savedMember)
        return savedMember
    }

    override fun findMemberByName(name: String): Member? {
        return map.values.find { it.name == name  }
    }
}
