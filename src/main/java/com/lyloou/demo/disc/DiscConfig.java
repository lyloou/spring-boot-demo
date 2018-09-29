package com.lyloou.demo.disc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:config/disc.properties")
public class DiscConfig {


    @Autowired
    Environment env;

    @Bean
    public Disc disc() {
        Disc disc = new Disc();
        System.out.println("==> use environment: env");
        disc.setTitle(env.getProperty("disc.title"));
        disc.setArtist(env.getProperty("disc.artist"));
        return disc;
    }

    @Bean
    public Disc discEnv(@Value("${disc.title}") String title,
                        @Value("${disc.artist}") String artist) {
        System.out.println("==> use annotation: value");
        Disc disc = new Disc();
        disc.setTitle(title);
        disc.setArtist(artist);
        return disc;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
