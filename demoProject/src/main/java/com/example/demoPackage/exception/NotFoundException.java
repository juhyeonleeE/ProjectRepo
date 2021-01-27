package com.example.demoPackage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String code;
	private String message;

//	public NotFoundException() {
//	}
//
//	public NotFoundException(String message) {
//		super(message);
//	}
//
//	public NotFoundException(String message, Throwable cause) {
//	    super(message, cause);
//	}
	
	public NotFoundException(String code, String message) {
	    super();
	    this.setCode(code);
	    this.setMessage(message);
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getMessage() {
        return this.message;
    }
	
	public void setMessage(String message) {
		this.message = message;
	}

}