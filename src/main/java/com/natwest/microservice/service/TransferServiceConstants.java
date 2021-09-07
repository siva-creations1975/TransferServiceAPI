package com.natwest.microservice.service;

import java.math.BigDecimal;

public class TransferServiceConstants {
	public static Long DEFAULT_SOURCE_ACCOUNT_NUMBER = 1001L;
	public static BigDecimal DEFAULT_SOURCE_ACCOUNT_BALANCE = new BigDecimal(1000);
	public static Long DEFAULT_DESTINATION_ACCOUNT_NUMBER = 1002L;
	public static BigDecimal DEFAULT_DESTINATION_ACCOUNT_BALANCE = new BigDecimal(1000);

	public static String DESTINATION_ACCOUNT_SHOULD_BE_DIFFERENT = "Destination account should be differnt";
	public static String SOURCE_ACCOUNT_IS_NOT_FOUND = "Source account is not found";
	public static String DESTINATION_ACCOUNT_IS_NOT_FOUND = "Destination account is not found";
	public static String NO_SUFFICIENT_FUNDS_IN_THE_ACCOUNT = "There is no sufficient funds in the account";
	
	
}
