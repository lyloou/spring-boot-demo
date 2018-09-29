package com.lyloou.demo.disc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:config/disc.properties")
public class DiscConfig {

    @Autowired
    Environment env;

    @Bean
    public Disc disc() {
        Disc disc = new Disc();
        disc.setTitle(env.getProperty("disc.title"));
        disc.setArtist(env.getProperty("disc.artist"));
        return disc;
    }
}
