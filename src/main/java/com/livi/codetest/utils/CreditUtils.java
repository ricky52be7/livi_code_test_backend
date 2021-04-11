package com.livi.codetest.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.livi.codetest.db.repository.RuleRepository;
import com.livi.codetest.enums.RuleType;
import com.livi.codetest.factory.RuleFactory;
import com.livi.codetest.models.CompanyTypeRule;
import com.livi.codetest.models.EmployeeRule;
import com.livi.codetest.models.RuleWrapper;
import com.livi.codetest.models.YearsOperatedRule;

@Component
public class CreditUtils {

	@Autowired
	private RuleRepository ruleRepository;

	public int calculateCreditAssessmentScore(EmployeeRule employeeRule, CompanyTypeRule companyTypeRule, YearsOperatedRule yearsOperatedRule) {
		int value = 0;
		if (employeeRule != null) value += employeeRule.getValue();
		if (companyTypeRule != null) value += companyTypeRule.getValue();
		if (yearsOperatedRule != null) value += yearsOperatedRule.getValue();
		return value;
	}

	@SuppressWarnings("unchecked")
	public <T> RuleWrapper<T> getRuleFromList(RuleType type, T valueOfT) {
		List<RuleWrapper<T>> ruleList = ruleRepository.findRuleByType(type).stream()
				.flatMap((r) -> {
					RuleWrapper<T> rule = RuleFactory.create(r);
					if (rule == null) {
						return Stream.empty();
					} else {
						return Stream.of(rule);
					}
				})
				.filter(r -> r.checkRule(valueOfT))
				.collect(Collectors.toList());
		
		if (ruleList.isEmpty()) {
			return null;
		} else {
			return ruleList.get(0);
		}

	}
}
