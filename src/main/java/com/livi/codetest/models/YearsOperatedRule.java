package com.livi.codetest.models;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

public class YearsOperatedRule extends RuleWrapper<Integer> {
	
	private int max;
	private int min;

	public static YearsOperatedRule create(String config) {
		try {
			return new YearsOperatedRule(config);
		} catch(Exception e) {
			return null;
		}
	}
	
	private YearsOperatedRule(String config) throws JsonProcessingException, IOException {
		JsonNode jsonNode = new ObjectMapper().readTree(config);
		min = jsonNode.get("min").asInt();
		max = jsonNode.get("max").asInt();
		value = jsonNode.get("value").asInt();
	}
	
	
	@Override
	public boolean checkRule(Integer valueOfT) {
		return min <= valueOfT && valueOfT <= max;
	}
	
}
