package com.livi.codetest.db.repository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.livi.codetest.db.entity.Rule;
import com.livi.codetest.enums.RuleType;


// In real env, it should be a DB repository
public interface RuleRepository {
	List<Rule> findRuleByType(RuleType ruleType);
}
