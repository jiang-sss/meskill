package com.jiang.meskill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.jiang.meskill"})
@MapperScan("com.jiang.meskill.dao")
public class MeskillApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeskillApplication.class, args);
    }

}
