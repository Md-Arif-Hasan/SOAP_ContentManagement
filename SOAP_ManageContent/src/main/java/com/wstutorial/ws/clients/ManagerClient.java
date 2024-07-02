package com.wstutorial.ws.clients;

import com.wstutorial.ws.commonservice.StatusCodeResponse;

import com.wstutorial.ws.contentservice.GetContentsResponse;
import com.wstutorial.ws.logservice.LogsRequest;
import com.wstutorial.ws.managementservice.CreateManagerRequest;
import com.wstutorial.ws.managementservice.CreateManagerResponse;
import com.wstutorial.ws.managementservice.GetManagerRequest;
import com.wstutorial.ws.managementservice.GetManagerResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class ManagerClient extends WebServiceGatewaySupport{
    public GetManagerResponse getManagerRequest( GetManagerRequest getManagerRequest) {
        return (GetManagerResponse) getWebServiceTemplate()
                .marshalSendAndReceive(getManagerRequest);
    }

    public StatusCodeResponse  createManagerResponse(CreateManagerRequest createManagerRequest) {
        return (StatusCodeResponse) getWebServiceTemplate()
                .marshalSendAndReceive(createManagerRequest);
    }


}
