package com.example.notice.domain

import jakarta.persistence.Entity

@Entity
class Member(
    val name: String,

    id: Long = 0L
) : BaseEntity(id) {
}