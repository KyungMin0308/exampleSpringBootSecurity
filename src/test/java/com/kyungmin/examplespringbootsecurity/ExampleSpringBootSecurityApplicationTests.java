package com.kyungmin.examplespringbootsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class ExampleSpringBootSecurityApplicationTests {

    @Autowired
    private PasswordEncoder encoder;

    //패스워드 암호화가 제대로 수행되는지 테스트
    @Test
    void generatedHashedPassword() {
        String pwd = encoder.encode("samplepw");
        System.out.println("======Encoded Password=====");
        System.out.println(pwd);
    }

    @Test
    void contextLoads() {
    }

}
