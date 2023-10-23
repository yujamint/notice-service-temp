package com.example.notice.controller

import com.example.notice.controller.response.ChannelResponse
import com.example.notice.repository.ChannelRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/channels")
class ChannelController(
    val channelRepository: ChannelRepository
) {

    @GetMapping
    fun getChannels(): ResponseEntity<List<ChannelResponse>> {
        val channels = channelRepository.findAll()
        return ResponseEntity.ok(channels.map(::ChannelResponse))
    }
}
