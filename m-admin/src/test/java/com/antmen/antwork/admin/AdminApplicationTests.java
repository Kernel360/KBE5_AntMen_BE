package com.antmen.antwork.admin;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdminApplicationTests {

    static {
        Dotenv dotenv = Dotenv.configure()
                .directory(System.getProperty("user.dir")) // .env가 프로젝트 루트에 있는 경우
                .ignoreIfMissing()
                .load();

        dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue())
        );
    }

    @Test
    void contextLoads() {
        System.out.println("MYSQL_PORT = " + System.getProperty("MYSQL_PORT"));
    }

}
