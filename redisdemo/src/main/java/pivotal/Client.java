package pivotal;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Client {

    @Autowired
//    @Qualifier("my-client-template")
    RedisTemplate template;
    
    public String doTest() {
    	String myKey = UUID.randomUUID().toString();
    	template.opsForValue().set(myKey, "hoge-serial");
 //   	template.opsForValue().get(myKey);
//    	return myKey;
    	return (String) template.opsForValue().get(myKey);
    }
}
 
    
