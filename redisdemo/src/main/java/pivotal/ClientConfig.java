package pivotal;



import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;


@Configuration
public class ClientConfig{
//public class ClientConfig extends AbstractCloudConfig{
	
//	@Bean
//	public RedisConnectionFactory redisFactory(){
//		// redisdemo: service instance
//	    return connectionFactory().redisConnectionFactory("redisdemo");
//	}
//	
//	@Bean(name = "my-client-template")
//    public RedisTemplate redisTemplate() {
//        return new StringRedisTemplate(redisFactory());
//    }

}
