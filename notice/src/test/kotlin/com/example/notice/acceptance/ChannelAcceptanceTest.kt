package com.example.notice.acceptance

import com.example.notice.repository.ChannelRepository
import io.restassured.RestAssured
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChannelIntegrationTest(
    @LocalServerPort var port: Int,
    @Autowired val channelRepository: ChannelRepository
) {

    @BeforeEach
    fun setUp() {
        RestAssured.port = this.port
    }

    @Test
    fun 모든_공지_채널을_조회한다() {
        // given
        val expect = channelRepository.findAll()

        // when
        val result = RestAssured.given().log().all()
            .`when`()
            .get("/channels")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract()

        // then
        Assertions.assertThat(result).usingRecursiveComparison()
            .comparingOnlyFields("channelName", "channelId")
            .isEqualTo(expect)
    }
}
