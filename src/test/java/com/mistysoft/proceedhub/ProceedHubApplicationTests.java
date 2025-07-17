package com.mistysoft.proceedhub;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProceedHubApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("🔍 DEBUG ENVIRONMENT VARIABLES:");
        System.out.println("🔧 PORT: " + System.getenv("PORT"));
        System.out.println("🔐 JWT_TOKEN: " + System.getenv("JWT_TOKEN"));
        System.out.println("⏱️ JWT_EXPIRATION: " + System.getenv("JWT_EXPIRATION"));
    }

}