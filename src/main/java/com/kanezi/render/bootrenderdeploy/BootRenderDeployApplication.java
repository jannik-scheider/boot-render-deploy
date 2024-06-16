package com.kanezi.render.bootrenderdeploy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

@SpringBootApplication
public class BootRenderDeployApplication {

	private static final AtomicBoolean HEALTH_STATUS = new AtomicBoolean(true);

	public static void main(String[] args) {
		SpringApplication.run(BootRenderDeployApplication.class, args);
	}

	@Bean
	public HealthIndicator customHealthIndicator() {
		return () -> HEALTH_STATUS.get() ? Health.up().build() : Health.down().build();
	}

	@RestController
	@RequestMapping("/")
	static class HomeController {
		@GetMapping
		String showTimestamp() {
			return LocalDateTime.now().toString();
		}

		@GetMapping("/toggleHealth")
		String toggleHealth() {
			boolean currentStatus = HEALTH_STATUS.get();
			HEALTH_STATUS.set(!currentStatus);
			return "Health status set to " + (currentStatus ? "DOWN" : "UP");
		}
	}
}
