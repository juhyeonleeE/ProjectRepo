package com.example.demoPackage.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
    public Map<String, Object> error400(NotFoundException e) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", e.getCode());
		result.put("메세지", e.getMessage());
        return result;
    }
	
}