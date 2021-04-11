package com.livi.codetest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = DemoApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class SpringIntegrationTest {
	protected static final String BASE_URL = "http://localhost:8080";
	protected ResponseResults latestResponse = null;

	@Autowired
	protected RestTemplate restTemplate;

	protected void executeGet(String url, Map<String, String> customHeaders, Map<String, String> params) throws IOException {
//		String credentials = "code-test:secret";
//		String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));
		
		final Map<String, String> headers = new HashMap<>();
//		headers.put("Accept", "application/json");
		if (customHeaders != null) {
			customHeaders.forEach((Key, value) -> headers.put(Key, value));	
		}
		
		
//		headers.put("Authorization", "Basic " + encodedCredentials);
		final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
		final DefaultResponseErrorHandler errorHandler = new DefaultResponseErrorHandler() {
		    public boolean hasError(ClientHttpResponse response) throws IOException {
		        HttpStatus statusCode = response.getStatusCode();
		        return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
		    }
		};
		final HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		if (params != null) {
			params.forEach((key, value) -> builder.queryParam(key, value));
		}

		restTemplate.setRequestFactory(httpRequestFactory);
		restTemplate.setErrorHandler(errorHandler);
		latestResponse = restTemplate.execute(builder.toUriString(), HttpMethod.GET, requestCallback,
				new ResponseExtractor<ResponseResults>() {
					@Override
					public ResponseResults extractData(ClientHttpResponse response) throws IOException {
						return (new ResponseResults(response));
					}
				});

	}

	protected void executePost(String url, Map<String, String> customHeaders, Map<String, String> params) throws IOException {
		final Map<String, String> headers = new HashMap<>();
//		headers.put("Accept", "application/json");
		if (customHeaders != null) {
			customHeaders.forEach((Key, value) -> headers.put(Key, value));	
		}
		
		final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
		final DefaultResponseErrorHandler errorHandler = new DefaultResponseErrorHandler() {
		    public boolean hasError(ClientHttpResponse response) throws IOException {
		        HttpStatus statusCode = response.getStatusCode();
		        return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
		    }
		};
		final HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		if (params != null) {
			params.forEach((key, value) -> builder.queryParam(key, value));
		}
		
		if (restTemplate == null) {
			restTemplate = new RestTemplate();
		}

		restTemplate.setRequestFactory(httpRequestFactory);
		restTemplate.setErrorHandler(errorHandler);
		latestResponse = restTemplate.execute(builder.build().toUriString(), HttpMethod.POST, requestCallback,
				new ResponseExtractor<ResponseResults>() {
					@Override
					public ResponseResults extractData(ClientHttpResponse response) throws IOException {
//						if (errorHandler.hadError) {
//							return (errorHandler.getResults());
//						} else {
//							return (new ResponseResults(response));
//						}
						return (new ResponseResults(response));
					}
				});

	}

	private class ResponseResultErrorHandler implements ResponseErrorHandler {
		private ResponseResults results = null;
		private Boolean hadError = false;

		private ResponseResults getResults() {
			return results;
		}

		@Override
		public boolean hasError(ClientHttpResponse response) throws IOException {
			hadError = response.getRawStatusCode() >= 400;
			return hadError;
		}

		@Override
		public void handleError(ClientHttpResponse response) throws IOException {
			results = new ResponseResults(response);
		}
	}
}
