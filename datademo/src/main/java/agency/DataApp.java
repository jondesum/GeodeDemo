package agency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableCircuitBreaker
public class DataApp {

    @Autowired
    DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(DataApp.class, args);
    }

    @RequestMapping("/")
    public String guide() {
        String message = dataSource.getGuide();
        return String.format("Your guide will be: %s", message);
    }

}
