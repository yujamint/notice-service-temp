package com.example.notice.domain

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.Lob
import jakarta.persistence.ManyToOne

@Entity
class Notice(
    val title: String,

    @Lob
    val content: String,

    @ManyToOne
    @JoinColumn(name = "memberId")
    val member: Member,

    id: Long = 0L
) : BaseEntity(id) {
}
