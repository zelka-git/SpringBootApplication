package com.spring_boot_application.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
public class ReportingController {

    public StreamingResponseBody generateReport(){
        return outputStream -> {

        };
    }

}
