package io.waggeh.waggeh.config;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    private String cloudName = "dvkw6np5u";
    private String apiKey = "499438962448363";
    private String apiSecret = "OeDrd6dM3rQ4dlReDLxsQXer91k";
    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name",cloudName,
                "api_key",apiKey,
                "api_secret",apiSecret
        ));
    }
}
