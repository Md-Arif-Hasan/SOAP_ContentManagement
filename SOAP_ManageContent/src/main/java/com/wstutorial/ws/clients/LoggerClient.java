package com.wstutorial.ws.clients;

import com.wstutorial.ws.commonservice.StatusCodeResponse;
import com.wstutorial.ws.logservice.LogsRequest;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class LoggerClient extends WebServiceGatewaySupport{
    public StatusCodeResponse createLog(LogsRequest logsRequest) {
        return (StatusCodeResponse) getWebServiceTemplate()
                .marshalSendAndReceive(logsRequest);
    }
}
