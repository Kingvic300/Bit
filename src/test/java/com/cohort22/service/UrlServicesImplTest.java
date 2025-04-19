package com.cohort22.service;

import com.cohort22.data.model.Url;
import com.cohort22.data.repository.UrlRepository;
import com.cohort22.dtos.request.UrlRequest;
import com.cohort22.dtos.response.UrlResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UrlServicesImplTest {

    @Autowired
    private UrlServices urlServices;

    @Test
    public void testThatShortUrlCanBeGenerated() {
       String url = "https://chatgpt.com/c/6802a3ed-413c-8011-85dd-d8e67d133d8f";
       UrlRequest urlRequest = new UrlRequest();
       urlRequest.setOriginalUrl(url);
       UrlResponse urlResponse = urlServices.generateShortUrl(urlRequest);
       System.out.println(urlResponse.getShortURL());
       assertNotNull(urlResponse);
       assertEquals(url,urlRequest.getOriginalUrl());
//       assertTrue(urlResponse.getShortURL().contains("http://localhost:9090/"));
    }
}