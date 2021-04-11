package com.livi.codetest.db.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.livi.codetest.db.entity.Rule;
import com.livi.codetest.db.repository.RuleRepository;
import com.livi.codetest.enums.RuleType;

@Service
public class RuleRepositoryImpl implements RuleRepository {

	@Override
	public List<Rule> findRuleByType(RuleType ruleType) {
		List<Rule> ruleList = new ArrayList<>();
		
		switch (ruleType) {
		case NUMBER_OF_EMPLOYEE:
			ruleList.add(new Rule(RuleType.NUMBER_OF_EMPLOYEE, "{\"min\":1,\"max\":1,\"value\":1}"));
			ruleList.add(new Rule(RuleType.NUMBER_OF_EMPLOYEE, "{\"min\":2,\"max\":5,\"value\":2}"));
			ruleList.add(new Rule(RuleType.NUMBER_OF_EMPLOYEE, "{\"min\":6,\"max\":10,\"value\":3}"));
			ruleList.add(new Rule(RuleType.NUMBER_OF_EMPLOYEE, "{\"min\":11,\"max\":50,\"value\":5}"));
			ruleList.add(new Rule(RuleType.NUMBER_OF_EMPLOYEE, "{\"min\":51,\"max\":200,\"value\":8}"));
			ruleList.add(new Rule(RuleType.NUMBER_OF_EMPLOYEE, "{\"min\":201,\"max\":999999999,\"value\":13}"));
			break;
			
		case COMPANY_TYPE:
			ruleList.add(new Rule(RuleType.COMPANY_TYPE, "{\"companyType\":\"Sole Proprietorship\",\"value\":1}"));
			ruleList.add(new Rule(RuleType.COMPANY_TYPE, "{\"companyType\":\"Partnership\",\"value\":3}"));
			ruleList.add(new Rule(RuleType.COMPANY_TYPE, "{\"companyType\":\"Limited Liability Company\",\"value\":5}"));
			ruleList.add(new Rule(RuleType.COMPANY_TYPE, "{\"companyType\":\"Others\",\"value\":0}"));
			break;
			
		case NUMBER_OF_YEARS_OPERATED:
			ruleList.add(new Rule(RuleType.NUMBER_OF_YEARS_OPERATED, "{\"min\":0,\"max\":1,\"value\":1}"));
			ruleList.add(new Rule(RuleType.NUMBER_OF_YEARS_OPERATED, "{\"min\":2,\"max\":3,\"value\":2}"));
			ruleList.add(new Rule(RuleType.NUMBER_OF_YEARS_OPERATED, "{\"min\":4,\"max\":6,\"value\":3}"));
			ruleList.add(new Rule(RuleType.NUMBER_OF_YEARS_OPERATED, "{\"min\":7,\"max\":10,\"value\":5}"));
			ruleList.add(new Rule(RuleType.NUMBER_OF_YEARS_OPERATED, "{\"min\":11,\"max\"999999999:,\"value\":13}"));
			break;
			
		default:
			break;
		}
		
		
		return ruleList;
	}

}
