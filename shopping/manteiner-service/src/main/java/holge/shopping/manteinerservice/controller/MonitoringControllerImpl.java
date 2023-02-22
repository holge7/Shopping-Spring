package holge.shopping.manteinerservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doc/monitoring")
public class MonitoringControllerImpl {
	
	@GetMapping("/zipkin")
	public String zipkin() {
		return "http://localhost:9411";
	}
	
}
