package com.wstutorial.ws.clients;

import com.wstutorial.ws.commonservice.StatusCodeResponse;
import com.wstutorial.ws.contentservice.GetContentRequest;
import com.wstutorial.ws.contentservice.GetContentResponse;
import com.wstutorial.ws.contentservice.GetContentsRequest;
import com.wstutorial.ws.contentservice.GetContentsResponse;

import com.wstutorial.ws.contentservice.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class ContentClient extends WebServiceGatewaySupport{
    public GetContentsResponse getContents( GetContentsRequest  getContentsRequest) {
        return (GetContentsResponse) getWebServiceTemplate()
                .marshalSendAndReceive(getContentsRequest);
    }

    public StatusCodeResponse createContent(CreateContentRequest createContentRequest) {
        return (StatusCodeResponse) getWebServiceTemplate()
                .marshalSendAndReceive(createContentRequest);
    }
}