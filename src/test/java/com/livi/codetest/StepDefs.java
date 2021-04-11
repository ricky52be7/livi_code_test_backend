package com.livi.codetest;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs extends SpringIntegrationTest {
	
	@When("The calculateCreditAssessmentScore API is called with {int}, {string}, {int} parameters")
	public void calculator_is_called(int numberOfEmployees, String companyType, int numberOfYearsOperated) throws Throwable {
		// Oauth2
		Map<String, String> oauthMap = new HashMap<String, String>();
		oauthMap.put("username", "user_1");
		oauthMap.put("password", "123456");
		oauthMap.put("grant_type", "password");
		oauthMap.put("scope", "select");
		oauthMap.put("client_id", "code_test");
		oauthMap.put("client_secret", "secret");
		executeGet(BASE_URL + "/oauth/token", null, oauthMap);
		
		JsonNode jsonNode = new ObjectMapper().readTree(latestResponse.getBody());
		String accessToken = jsonNode.get("access_token").asText();
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "Bearer " + accessToken);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("numberOfEmployees", String.valueOf(numberOfEmployees));
		params.put("companyType", companyType);
		params.put("numberOfYearsOperated", String.valueOf(numberOfYearsOperated));
		
		executePost(BASE_URL + "/creditservice/v1/calculator", headers, params);
	}
	
	@When("The calculateCreditAssessmentScore API is called with {int}, {string}, {int} parameters and the user is not authenticated")
	public void calculator_is_called_with_not_authenticate(int numberOfEmployees, String companyType, int numberOfYearsOperated) throws Throwable {
		Map<String, String> oauthMap = new HashMap<String, String>();
		oauthMap.put("username", "user_2");
		oauthMap.put("password", "123456");
		oauthMap.put("grant_type", "password");
		oauthMap.put("scope", "select");
		oauthMap.put("client_id", "code_test");
		oauthMap.put("client_secret", "secret");
		executeGet(BASE_URL + "/oauth/token", null, oauthMap);
		
		JsonNode jsonNode = new ObjectMapper().readTree(latestResponse.getBody());
		String accessToken = jsonNode.get("access_token").asText();
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "Bearer " + accessToken);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("numberOfEmployees", String.valueOf(numberOfEmployees));
		params.put("companyType", companyType);
		params.put("numberOfYearsOperated", String.valueOf(numberOfYearsOperated));
		
		executePost(BASE_URL + "/creditservice/v1/calculator", headers, params);
	}
	
	@When("The calculateCreditAssessmentScore API is called with {int}, {string}, {int} parameters and the user is not authorized")
	public void calculator_is_called_with_ot_authorized(int numberOfEmployees, String companyType, int numberOfYearsOperated) throws Throwable {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "Bearer a1abe747-8a67-4087-a927-de84cedbfaab"); // wrong token
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("numberOfEmployees", String.valueOf(numberOfEmployees));
		params.put("companyType", companyType);
		params.put("numberOfYearsOperated", String.valueOf(numberOfYearsOperated));
		
		executePost(BASE_URL + "/creditservice/v1/calculator", headers, params);
	}
	
	@Then("The credit assessment score should match {int}")
    public void the_score_should_match(int creditScore) throws Throwable {
		JsonNode jsonNode = new ObjectMapper().readTree(latestResponse.getBody());
		int score = jsonNode.get("creditScore").asInt();
		assertEquals(creditScore, score);
    }
	
	@Then("The response http code shoud be {int}")
    public void the_http_code_should_be(int httpCode) throws Throwable {
		assertEquals(latestResponse.getTheResponse().getRawStatusCode(), httpCode);
    }
}
