package com.project.studentLibraryManagement.Cloudinary;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class CloudinaryConfig {
    @Value("cloudName")
    private String cloudName;
    @Value("apiKey")
    private String apiKey;
    @Value("apiSecret")
    private String apiSecret;

//    @Bean
//    public CardService cardService(){
//        return new CardService();
//    }

    @Bean
    public Cloudinary cloudinary() {
        HashMap<String,String> map=new HashMap<>();
        map.put("cloud_name",this.cloudName);
        map.put("api_key",this.apiKey);
        map.put("api_secret",this.apiSecret);
        return new Cloudinary(map);
    }
}
