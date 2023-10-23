package com.example.notice.controller.response

import com.example.notice.domain.Channel

data class ChannelResponse(val channelName: String, val channelId: String) {

    constructor(channel: Channel) : this(
        channel.channelName, channel.channelId
    )
}
