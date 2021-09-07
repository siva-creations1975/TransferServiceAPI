package com.natwest.microservices.test.base;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.natwest.microservice.model.TransferRequest;
import com.natwest.microservice.model.TransferResponse;
import com.natwest.microservice.service.TransferServiceConstants;

public class TransferServiceTestBase {

	private String SERVICE_URL = "http://localhost:8012/ms/app/v1/transfer-service";
	private RestTemplate REST_TEMPLATE = new RestTemplate();
	private ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	public TransferResponse invokeTransferServiceAPI(TransferRequest transferRequest) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<TransferRequest> entity = new HttpEntity<>(transferRequest, headers);

		ResponseEntity<TransferResponse> response = REST_TEMPLATE.exchange(SERVICE_URL, HttpMethod.POST, entity,
				TransferResponse.class);

		return response.getBody();
	}

	public void verifySuccessfulTransfer(TransferRequest transferRequest, TransferResponse transferResponse)
			throws Exception {
		assertEquals(transferRequest.getTransactionModel().getSrcAccountNo(),
				transferResponse.getSrcAccountModel().getAccountNo());
		assertEquals(transferRequest.getTransactionModel().getDestAccountNo(),
				transferResponse.getDestAccountModel().getAccountNo());
		BigDecimal expectedSrcBalance = TransferServiceConstants.DEFAULT_SOURCE_ACCOUNT_BALANCE
				.subtract(transferRequest.getTransactionModel().getAmount());
		assertEquals(expectedSrcBalance, transferResponse.getSrcAccountModel().getBalance());
		BigDecimal expectedDestBalance = TransferServiceConstants.DEFAULT_DESTINATION_ACCOUNT_BALANCE
				.add(transferRequest.getTransactionModel().getAmount());
		assertEquals(expectedDestBalance, transferResponse.getDestAccountModel().getBalance());
	}

	public void verifyUnsuccessfulTransfer(String expected, String errorResponse, String key) throws Exception {
		String actual = getValue(errorResponse, key);
		assertEquals(expected, actual);
	}

	private String getValue(String jsonString, String key) throws IOException {
		return OBJECT_MAPPER.readTree(jsonString).get(key).asText();
	}
}
