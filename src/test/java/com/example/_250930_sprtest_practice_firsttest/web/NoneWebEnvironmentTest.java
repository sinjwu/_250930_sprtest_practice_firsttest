package com.example._250930_sprtest_practice_firsttest.web;

import com.example._250930_sprtest_practice_firsttest.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class NoneWebEnvironmentTest {

    @Autowired
    private UserService userService;

    @Test
    void serviceLayerOnlyWithoutWeb() {
        assertThat(userService).isNotNull();

        System.out.println("✅ 웹 환경 없이 서비스 계층 테스트");
        System.out.println("\uD83D\uDD27 웹 환경: NONE (웹 서버 시작하지 않음)");
    }

    @Test
    void focusOnBusinessLogic() {

        long initialCount = userService.getUserCount();

        assertThat(initialCount).isGreaterThanOrEqualTo(0);

        System.out.println("✅ 비즈니스 로직 테스트 집중 가능");
        System.out.println("\uD83D\uDCCA 초기 데이터 확인: " + initialCount);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            userService.getUserCount();
        }
        long endTime = System.currentTimeMillis();

        System.out.println("⏱\uFE0F 10회 호출 시간: " + (endTime = startTime) + "ms");
    }
}
