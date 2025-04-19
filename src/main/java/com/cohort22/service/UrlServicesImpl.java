package com.cohort22.service;

import com.cohort22.data.model.Url;
import com.cohort22.data.repository.UrlRepository;
import com.cohort22.dtos.request.UrlRequest;
import com.cohort22.dtos.response.UrlResponse;
import com.cohort22.exception.UrlAlreadyExistsException;
import com.cohort22.exception.UrlNotFoundException;
import com.cohort22.mapper.UrlMapper;
import com.cohort22.util.UrlGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UrlServicesImpl implements UrlServices {
    private final UrlRepository urlRepository;

    @Override
    public UrlResponse generateShortUrl(UrlRequest request) {
        String shortCode = UrlGenerator.generateShortURL();
        if(urlRepository.existsByShortCode(shortCode)){
            throw new UrlAlreadyExistsException("Url already exists");
        }
        Url url = new Url();
        url.setOriginalUrl(request.getOriginalUrl());
        url.setCreatedAt(LocalDateTime.now());
        url.setExpiresAt(LocalDateTime.now().plusDays(1));
        url.setShortCode(shortCode);
        urlRepository.save(url);
        return UrlMapper.mapToUrlResponse(url,"Created");
    }
    @Override
    public UrlResponse getOriginalUrl(String code) {
        Url url = urlRepository.findByShortCode(code)
                .orElseThrow(() -> new UrlNotFoundException("Url not found"));

        if (url.getExpiresAt().isBefore(LocalDateTime.now().minusDays(1))) {
            throw new UrlNotFoundException("Url has expired");
        }

        url.setClickCount(url.getClickCount() + 1);
        url.setExpiresAt(LocalDateTime.now().plusDays(1));
        urlRepository.save(url);
        return UrlMapper.mapToUrlResponse(url,"Found successfully");
    }
}
