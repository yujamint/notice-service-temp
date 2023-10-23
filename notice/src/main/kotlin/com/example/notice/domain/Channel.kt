package com.example.notice.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class Channel(
    @Column(nullable = false, unique = true)
    val channelName: String,

    @Column(nullable = false, unique = true)
    val channelId: String,

    id: Long = 0L
) : BaseEntity(id) {
}
