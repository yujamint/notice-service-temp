package com.example.notice.repository

import com.example.notice.domain.Channel
import org.springframework.data.repository.Repository

interface ChannelRepository : Repository<Channel, Long> {

    fun findAll(): List<Channel>

    fun getByChannelId(channelId: String): Channel
}
