package com.bank.credit.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CreditTypeFactory {
	static Map<String, CreditTypeOperation> operationMap = new HashMap<>();
	static {
		operationMap.put("personal credit", new PersonalCredit());
		operationMap.put("business credit", new BusinessCredit());
		operationMap.put("personal credit card", new PersonalCreditCard());
		operationMap.put("business credit card", new BusinessCreditCard());
	}
	
	public static Optional<CreditTypeOperation> getOperation(String operator) {
        return Optional.ofNullable(operationMap.get(operator));
    }
}
