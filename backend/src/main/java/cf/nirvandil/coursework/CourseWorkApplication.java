package cf.nirvandil.coursework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CourseWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseWorkApplication.class, args);
    }
}
