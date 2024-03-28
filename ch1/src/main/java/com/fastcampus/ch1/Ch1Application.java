package com.fastcampus.ch1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//이 프로그램은 웹에서 호출할 수 있는 프로그램이라고 등록
//controller로 등록된 프로그램은 spring 이 자동으로 static으로 변환시키기 때문에 따로 객체 생성을 하지 않아도 생성된다.
@SpringBootApplication
public class Ch1Application {

	public static void main(String[] args) {
		SpringApplication.run(Ch1Application.class, args);
	}
	@GetMapping
	public String hello(){
		return "Hello, Spring Boot";
	}
}
