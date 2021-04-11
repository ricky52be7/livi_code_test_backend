package com.livi.codetest.db.entity;

import com.livi.codetest.enums.RuleType;

public class Rule {
	private RuleType ruleType;
	private String config;
	
	public Rule() {}
	
	public Rule(RuleType ruleType, String config) {
		this.ruleType = ruleType;
		this.config = config;
	}
	
	public RuleType getRuleType() {
		return ruleType;
	}
	
	public void setRuleType(RuleType ruleType) {
		this.ruleType = ruleType;
	}
	
	public String getConfig() {
		return config;
	}
	
	public void setConfig(String config) {
		this.config = config;
	}
}
