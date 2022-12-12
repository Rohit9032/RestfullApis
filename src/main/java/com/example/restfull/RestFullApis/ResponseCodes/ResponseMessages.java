package com.example.restfull.RestFullApis.ResponseCodes;

public class ResponseMessages {

	String response;

	public ResponseMessages(String response) {
		super();
		this.response = response;
	}

	public String getSuccessMsg() {
		return response;
	}

	@Override
	public String toString() {
		return "ResponseMessages [response=" + response + "]";
	}

	
	
}
