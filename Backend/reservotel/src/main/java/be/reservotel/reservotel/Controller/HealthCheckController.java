package be.reservotel.reservotel.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthCheckController {

    @PostMapping("/check")
    public Map<String, String> postHealthCheck(@RequestBody Map<String, String> payload) {
        return Map.of("status", "success", "username", payload.get("username"), "password", payload.get("password"));
    }
}