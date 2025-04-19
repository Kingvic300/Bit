package com.cohort22.service;

import com.cohort22.data.model.Url;
import com.cohort22.dtos.request.UrlRequest;
import com.cohort22.dtos.response.UrlResponse;

public interface UrlServices {
    UrlResponse generateShortUrl(UrlRequest request);

    UrlResponse getOriginalUrl(String code);
}
