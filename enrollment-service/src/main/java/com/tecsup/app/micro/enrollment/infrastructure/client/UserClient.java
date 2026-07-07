package com.tecsup.app.micro.enrollment.infrastructure.client;
import com.tecsup.app.micro.enrollment.domain.exception.UserServiceException;
import com.tecsup.app.micro.enrollment.infrastructure.client.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component @RequiredArgsConstructor @Slf4j
public class UserClient {
    private final RestTemplate restTemplate;
    @Value("${user.service.url}") private String userServiceUrl;
    public UserDTO getUserById(Long userId) {
        String url = userServiceUrl + "/api/users/" + userId;
        log.info("[ENROLLMENT-SERVICE] RestTemplate -> GET {} userId={}", url, userId);
        try {
            UserDTO user = restTemplate.getForObject(url, UserDTO.class);
            log.info("[ENROLLMENT-SERVICE] User validated OK: id={}, name={}", user.getId(), user.getName());
            return user;
        } catch (Exception e) {
            log.error("[ENROLLMENT-SERVICE] user-service error: {}", e.getMessage());
            throw new UserServiceException("User id=" + userId + " not found or user-service unavailable");
        }
    }
}
