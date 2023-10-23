package com.example.notice.service

import com.slack.api.Slack
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class SlackService {

    private val log = LoggerFactory.getLogger(javaClass)

    @Value("\${slack.bot.token}")
    private lateinit var botToken: String

    @Value("\${slack.user.token}")
    private lateinit var userToken: String

    fun sendMessageByUser(channelId: String, content: String) {
        val client = Slack.getInstance().methods()
        runCatching {
            client.chatPostMessage {
                it.token(userToken)
                    .channel(channelId)
                    .text(content)
            }
        }.onFailure { e ->
            log.error("Slack Send Error: {}", e.message, e)
        }
    }
}