package com.cohort22.controller;

import com.cohort22.dtos.request.UrlRequest;
import com.cohort22.dtos.response.UrlResponse;
import com.cohort22.service.UrlServices;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UrlController {

    private final UrlServices urlServices;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("urlRequest", new UrlRequest());
        return "index";
    }

    @PostMapping("/shorten")
    public String generateShortUrl(@ModelAttribute UrlRequest urlRequest,
                                   HttpServletRequest request,
                                   Model model) {
        String baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), "");

        UrlResponse response = urlServices.generateShortUrl(urlRequest);

        response.setShortURL(baseUrl + "/" + response.getShortURL());

        model.addAttribute("response", response);
        return "result";
    }

    @GetMapping("/{shortCode}")
    public String redirectToOriginalUrl(@PathVariable String shortCode) {
        UrlResponse response = urlServices.getOriginalUrl(shortCode);
        return "redirect:" + response.getOriginalUrl();
    }
}
