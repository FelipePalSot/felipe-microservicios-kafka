package com.tecsup.app.micro.notification.infrastructure.web.controller;
import com.tecsup.app.micro.notification.application.service.NotificationApplicationService;
import com.tecsup.app.micro.notification.infrastructure.web.dto.NotificationResponse;
import com.tecsup.app.micro.notification.infrastructure.web.mapper.NotificationDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/notifications") @RequiredArgsConstructor @Slf4j
public class NotificationController {
    private final NotificationApplicationService service;
    private final NotificationDtoMapper mapper;
    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getAll() {
        return ResponseEntity.ok(service.getAll().stream().map(mapper::toResponse).toList());
    }
    @GetMapping("/health")
    public ResponseEntity<String> health() { return ResponseEntity.ok("Notification Service OK"); }
}
