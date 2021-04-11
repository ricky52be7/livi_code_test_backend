package com.livi.codetest.models;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

public class CompanyTypeRule extends RuleWrapper<String> {
	private String companyType;
	
	public static CompanyTypeRule create(String config) {
		try {
			return new CompanyTypeRule(config);
		} catch (Exception e) {
			return null;
		}
	}
	
	private CompanyTypeRule(String config) throws JsonProcessingException, IOException {
		JsonNode jsonNode = new ObjectMapper().readTree(config);
		companyType = jsonNode.get("companyType").asText();
		value = jsonNode.get("value").asInt();
	}

	@Override
	public boolean checkRule(String valueOfT) {
		return companyType.equalsIgnoreCase(valueOfT);
	}
	
}
