package com.example._250930_sprtest_practice_firsttest.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RandomPortWebEnvironmentTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void realServerStartsWithRandomPort() {
        assertThat(port).isGreaterThan(0);
        assertThat(restTemplate).isNotNull();

        System.out.println("✅ 실제 웹 서버 시작됨");
        System.out.println("\uD83C\uDF10 할당된 포트: " + port);
        System.out.println("\uD83D\uDD27 웹 환경: RANDOM_PORT");
    }

    @Test
    void httpRequestToActualServer() {

        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/users/count", String.class
        );

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();

        System.out.println("✅ HTTP 요청 성공");
        System.out.println("\uD83D\uDCE1 응답 상태: " + response.getStatusCode());
        System.out.println("\uD83D\uDCC4 응답 본문: " + response.getBody());
    }

    @Test
    void multipleEndpointsWork() {
        ResponseEntity<String> usersResponse = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/users", String.class
        );
        assertThat(usersResponse.getStatusCode().is2xxSuccessful()).isTrue();

        ResponseEntity<Long> countResponse = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/users/count", Long.class
        );
        assertThat(countResponse.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(countResponse.getBody()).isNotNull();

        System.out.println("✅ 다중 엔드포인트 테스트 성공");
        System.out.println("\uD83D\uDC65 사용자 목록 API: " + usersResponse.getStatusCode());
        System.out.println("\uD83D\uDCCA 사용자 수 API: " + countResponse.getBody());
    }
}
