package com.example._250930_sprtest_practice_firsttest.web;

import com.example._250930_sprtest_practice_firsttest.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureWebMvc
@ActiveProfiles("test")
public class MockWebEnvironmentTest {

    @Autowired
    private UserService userService;

    @Test
    void mockEnvironmentCharacteristics() {
        assertThat(userService).isNotNull();

        System.out.println("✅ MOCK 환경에서 서비스 계층 테스트 가능");
        System.out.println("\uD83D\uDD27 웹 환경: MOCK (실제 서버 시작하지 않음)");
    }

    @Test
    void serviceLayerWorksInMockEnvironment() {

        long userCount = userService.getUserCount();

        assertThat(userCount).isGreaterThanOrEqualTo(0);

        System.out.println("✅ MOCK 환경에서 데이터 액세스 정상 동작");
        System.out.println("\uD83D\uDCCA 사용자 수: " + userCount);
    }
}
