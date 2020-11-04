package com.globalClasses;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

public class ApiTools {
	public String hostName = ApiPaths.RESOURCES_API_JAVA_ENDPOINT;
	public ResponseEntity<String> response;
	public ResponseEntity<String> requestBody;
	public MediaType contentType = MediaType.APPLICATION_JSON;
	public RestTemplate restTemplate = new RestTemplate();
	public HttpHeaders headers = new HttpHeaders();

	public ResponseEntity<String> POSTMethod(String apiPath, String requestBody) {
		try {
			headers.add("User-Agent", "User-Agent");
			headers.setContentType(contentType);

			restTemplate.setErrorHandler(new ResponseErrorHandler() {
				@Override
				public boolean hasError(ClientHttpResponse response) throws IOException {
					return false;
				}

				@Override
				public void handleError(ClientHttpResponse response) throws IOException {
				}
			});
			HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
			response = restTemplate.exchange(hostName + apiPath, HttpMethod.POST, requestEntity, String.class);
		} catch (HttpClientErrorException e) {
			System.out.println(e.getMessage());
			response = new ResponseEntity<String>(((HttpStatusCodeException) e).getResponseBodyAsString(),
					((HttpStatusCodeException) e).getStatusCode());
		}
		return response;
	}

	public ResponseEntity<String> deleteMethod(String apiPath) {
		try {
			headers.setContentType(contentType);
			restTemplate.setErrorHandler(new ResponseErrorHandler() {
				@Override
				public boolean hasError(ClientHttpResponse responseDelete) throws IOException {
					return false;
				}

				@Override
				public void handleError(ClientHttpResponse responseDelete) throws IOException {
				}
			});
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			response = restTemplate.exchange(hostName + apiPath, HttpMethod.DELETE, requestEntity, String.class);
		} catch (HttpClientErrorException e) {
			System.out.println(e.getMessage());
			response = new ResponseEntity<String>(((HttpStatusCodeException) e).getResponseBodyAsString(),
					((HttpStatusCodeException) e).getStatusCode());
		}
		return response;
	}
}
