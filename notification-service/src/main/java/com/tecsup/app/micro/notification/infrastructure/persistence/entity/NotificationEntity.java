package com.tecsup.app.micro.notification.infrastructure.persistence.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity @Table(name = "notifications")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class NotificationEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name = "event_type", length = 50) private String eventType;
    @Column(name = "reference_id") private Long referenceId;
    @Column(columnDefinition = "TEXT", nullable = false) private String message;
    @Column(nullable = false) private Boolean sent = false;
    @Column(name = "created_at", nullable = false, updatable = false) private LocalDateTime createdAt;
    @PrePersist protected void onCreate() { createdAt = LocalDateTime.now(); if (sent == null) sent = false; }
}
