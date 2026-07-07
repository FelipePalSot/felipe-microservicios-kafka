package com.tecsup.app.micro.course;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
@SpringBootTest
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb",
    "spring.kafka.bootstrap-servers=localhost:9999"
})
class CourseServiceApplicationTests {
    @Test void contextLoads() {}
}
