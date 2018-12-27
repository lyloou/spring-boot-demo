package com.lyloou.demo.config;

import com.lyloou.demo.pojo.Disc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DiscConfig.class)
@SpringBootTest
public class DiscTests {

    @Autowired
    Disc disc;

    @Test
    public void printDisc() {
        assert disc.getTitle().equals("title1");
        assert disc.getArtist().equals("artist1");
    }

}
