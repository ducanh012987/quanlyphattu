package com.example.qlphattu.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary getCloudinary(){
        Map config = new HashMap();
        config.put("cloud_name", "dhwqziryq");
        config.put("api_key", "737964785746285");
        config.put("api_secret", "eGd9lx5f0dVelsTjabmZ4x4AB4A");
        config.put("secure", true);
        return new Cloudinary(config);
    }
}
