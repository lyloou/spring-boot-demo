package com.lyloou.demo.config;

import com.lyloou.demo.pojo.Disc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(DiscConfig.class);

    @Autowired
    Environment env;

    @Bean
    public Disc discEnv() {
        Disc disc = new Disc();
        logger.info("==> use environment: env");
        disc.setTitle(env.getProperty("disc.title2"));
        disc.setArtist(env.getProperty("disc.artist2"));
        return disc;
    }

    @Bean
    public Disc disc(@Value("${disc.title}") String title,
                     @Value("${disc.artist}") String artist) {
        logger.info("==> use annotation: value");
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
