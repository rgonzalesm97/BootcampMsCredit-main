package com.bank.credit.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.bank.credit.entity.Credit;


public class CreditFactory {
	
	static Map<String, Product> products = new HashMap<>();
	static {
		products.put("personal", new PersonalCredit());
		products.put("business", new BusinessCredit());
		products.put("personal credit card", new PersonalCreditCard());
		products.put("bussines credit card", new BusinessCreditCard());
	}
	
	public static Optional<Product> getProduct(Credit credit){
		return Optional.ofNullable(products.get(credit.getTypeCredit()));
	}
}
