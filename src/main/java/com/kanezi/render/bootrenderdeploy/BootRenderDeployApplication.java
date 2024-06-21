package com.kanezi.render.bootrenderdeploy;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.ScheduledFuture;

@SpringBootApplication
@EnableScheduling
public class BootRenderDeployApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootRenderDeployApplication.class, args);
	}

	// Test Controller
	@RestController
	@RequestMapping("/")
	static class HomeController {
		@GetMapping
		String showTimestamp() {

			LocalTime now = LocalTime.now();
			LocalTime targetTime = LocalTime.of(14, 46);
			if (now.getHour() == targetTime.getHour() && now.getMinute() == targetTime.getMinute()) {
				return "true";
			} else {
				return "false";
			}
			//return LocalDateTime.now().toString();
		}
	}

	// Task Scheduler Bean
	@Bean
	public ThreadPoolTaskScheduler taskScheduler() {
		return new ThreadPoolTaskScheduler();
	}

	// New Test Controller
	@RestController
	@RequestMapping("/test")
	static class TestController {

		@GetMapping
		public void testEndpoint(HttpServletResponse response) throws IOException {
			LocalTime now = LocalTime.now();
			if (now.getMinute() % 10 == 0 || now.getMinute() % 10 < 4){
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Simulated Error");
			} else {
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().write("OK");
			}
		}
	}
}
