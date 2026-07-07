package com.tecsup.app.micro.enrollment.infrastructure.client.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@Data @JsonIgnoreProperties(ignoreUnknown = true)
public class CourseDTO { private Long id; private String title; private Boolean published; }
