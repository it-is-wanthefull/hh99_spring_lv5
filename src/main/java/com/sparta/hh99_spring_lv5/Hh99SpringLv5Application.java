package com.sparta.hh99_spring_lv5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication//(exclude = SecurityAutoConfiguration .class)
//@EnableScheduling // 스프링 부트에서 스케줄러가 작동하게 합니다.
//@EnableJpaAuditing // 시간 자동 변경이 가능하도록 합니다.
public class Hh99SpringLv5Application {

    public static void main(String[] args) {
        SpringApplication.run(Hh99SpringLv5Application.class, args);
    }

}
