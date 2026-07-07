package com.tecsup.app.micro.course.infrastructure.web.controller;
import com.tecsup.app.micro.course.domain.exception.CourseNotFoundException;
import com.tecsup.app.micro.course.domain.exception.InvalidCourseDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleNotFound(CourseNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
    }
    @ExceptionHandler(InvalidCourseDataException.class)
    public ResponseEntity<Map<String,String>> handleInvalid(InvalidCourseDataException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> handleGeneral(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
    }
}
