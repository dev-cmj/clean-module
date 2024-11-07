package com.goodmit.framework.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final MessageSource messageSource;


    @RequestMapping("/hello")
    public String hello() {
        Locale locale = Locale.getDefault();
        return messageSource.getMessage("hello.world", null, locale);
    }

}
