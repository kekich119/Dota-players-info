package com.kekich.dotatest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kekich.dotatest.service.ParserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DotaTestApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(DotaTestApplication.class, args);

    }

}
