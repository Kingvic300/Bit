package com.cohort22.util;

import java.util.Random;

public class UrlGenerator {

    private static final String code =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789?/-+*||&^%$#@!~";
    private static final int SHORT_CODE_LENGTH = 6;

    public static String generateShortURL() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            sb.append(code.charAt(random.nextInt(code.length())));
        }
        return sb.toString();
    }
}
