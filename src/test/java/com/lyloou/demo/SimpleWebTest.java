package com.lyloou.demo;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = DemoApplication.class)
public class SimpleWebTest {
    @Value("${local.server.port}")
    private int port;


    @Test(expected = HttpClientErrorException.class)
    public void pageNotFound() {
        try {
            String url = "http://localhost:" + port + "/bogusPage";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getForObject(url, String.class);
            fail("Should result in HTTP 404");
        } catch (HttpClientErrorException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
            throw e;
        }
    }


    @Test
    public void httpClient() throws IOException, JSONException {
        String url = "http://localhost:" + port + "/bogusPage";

        Request request = new Request.Builder()
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        assert response.body() != null;
        String string = response.body().string();
        JSONObject object = new JSONObject(string);
        String status = object.optString("status");
        System.out.println(string);
        assertEquals(status, "404");
    }
}
