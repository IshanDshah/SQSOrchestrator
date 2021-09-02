package com.sqs.orchestrator.controller;



import org.springframework.boot.web.reactive.error.ErrorAttributes;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;

@Controller
public class AppErrorController implements ErrorController {

    /**
     * Error Attributes in the Application
     */


    private final static String ERROR_PATH = "/error";

    /**
     * Supports the HTML Error View
     * @param request
     * @return
     */
    @RequestMapping(value = ERROR_PATH)
    public String errorHtml(HttpServletRequest request) {
        return "Page Not found";
    }



}