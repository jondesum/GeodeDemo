package agency;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class DataSource {

    @Autowired
    @LoadBalanced
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getBackupGuide")
    public String getGuide() {
        return restTemplate.getForObject("https://gemproxy/doget", String.class);
    }

    public String getBackupGuide() {
        return restTemplate.getForObject("https://gemcache/doget", String.class);
//       return restTemplate.getForObject("https://redisapp/available", String.class);
    }

}
