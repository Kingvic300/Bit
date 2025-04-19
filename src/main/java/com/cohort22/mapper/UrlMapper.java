package com.cohort22.mapper;


import com.cohort22.data.model.Url;
import com.cohort22.dtos.request.UrlRequest;
import com.cohort22.dtos.response.UrlResponse;

import java.time.LocalDateTime;

public class UrlMapper {
    public static Url mapToUrl(UrlRequest urlRequest) {
        Url url = new Url();
        url.setOriginalUrl(urlRequest.getOriginalUrl());
        return url;
    }
    public static UrlResponse mapToUrlResponse(Url url, String message) {
        UrlResponse urlResponse = new UrlResponse();
        urlResponse.setShortURL(url.getShortCode());
        urlResponse.setExpiresAt(url.getExpiresAt());
        urlResponse.setOriginalUrl(url.getOriginalUrl());
        urlResponse.setMessage(message);
        return urlResponse;
    }
}
