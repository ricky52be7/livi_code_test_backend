package com.livi.codetest.factory;

import com.livi.codetest.db.entity.Rule;
import com.livi.codetest.models.CompanyTypeRule;
import com.livi.codetest.models.EmployeeRule;
import com.livi.codetest.models.RuleWrapper;
import com.livi.codetest.models.YearsOperatedRule;

public class RuleFactory {
	private RuleFactory() {}
	
	@SuppressWarnings("rawtypes")
	public static RuleWrapper create(Rule rule) {
		switch (rule.getRuleType()) {
		case NUMBER_OF_EMPLOYEE:
			return EmployeeRule.create(rule.getConfig());
		case COMPANY_TYPE:
			return CompanyTypeRule.create(rule.getConfig());
			
		case NUMBER_OF_YEARS_OPERATED:
			return YearsOperatedRule.create(rule.getConfig());
			
		default:
			return null;
		}
	}
}
