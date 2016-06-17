package pivotal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class RedisdemoApplication {

	@Autowired
	Client client;
	
    @RequestMapping("/available")
    public String guide() {
    	String message = client.doTest();
        return String.format(message);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(RedisdemoApplication.class, args);
	}
}
