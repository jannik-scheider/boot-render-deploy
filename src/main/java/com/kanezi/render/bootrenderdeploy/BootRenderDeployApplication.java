package com.kanezi.render.bootrenderdeploy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@SpringBootApplication
public class BootRenderDeployApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootRenderDeployApplication.class, args);
	}

	@RestController
	@RequestMapping("/")
	static class HomeController {
		@GetMapping
		String showTimestamp() {
			return LocalDateTime.now().toString();
		}
	}
}
