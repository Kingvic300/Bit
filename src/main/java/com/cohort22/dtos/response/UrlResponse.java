package com.cohort22.dtos.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UrlResponse {
    private String originalUrl;
    private String shortURL;
    private LocalDateTime expiresAt;
    private String message;

}
