package com.tecsup.app.micro.enrollment.infrastructure.client.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@Data @JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO { private Long id; private String name; private String email; }
