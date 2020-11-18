package com.globalClasses;

import org.springframework.http.ResponseEntity;

public class BasicSecurityUtil {
	public String requestBody, responseBody, apiResource;
	public ResponseEntity<String> response;
	public ApiTools ServiceApi;
	public String param;
	public String expectedMessage;
	public String method;
}
