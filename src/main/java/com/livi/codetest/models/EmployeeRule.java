package com.livi.codetest.models;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

public class EmployeeRule extends RuleWrapper<Integer> {
	private int min;
	private int max;
	
	public static EmployeeRule create(String config) {
		try {
			return new EmployeeRule(config);
		} catch (Exception e) {
			return null;
		}
	}
	
	private EmployeeRule(String config) throws JsonProcessingException, IOException {
		JsonNode jsonNode = new ObjectMapper().readTree(config);
		min = jsonNode.get("min").asInt();
		max = jsonNode.get("max").asInt();
		value = jsonNode.get("value").asInt();
	}
	
	public EmployeeRule(int min, int max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean checkRule(Integer valueOfT) {
		return min <= valueOfT && valueOfT <= max;
	}

}
