package com.wstutorial.ws;

//import com.wstutorial.ws.commonservice.StatusCode;
import com.wstutorial.ws.commonservice.StatusCodeResponse;
import com.wstutorial.ws.logservice.LogsRequest;
import com.wstutorial.ws.userservice.*;
import com.wstutorial.ws.userservice.User;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Endpoint
public class LogServiceEndpoint {
	private static final String NAMESPACE_URI = "http://www.wstutorial.com/ws/LogService";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "logsRequest" )
	@ResponsePayload
	public StatusCodeResponse createLog(@RequestPayload LogsRequest request)throws Exception  {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		System.out.println(dtf.format(now) + "------------ " + request.getContent() + " ------------");

		 StatusCodeResponse response = new StatusCodeResponse();
		response.setStatusCode("201 CREATED");
		return response;
	}
}