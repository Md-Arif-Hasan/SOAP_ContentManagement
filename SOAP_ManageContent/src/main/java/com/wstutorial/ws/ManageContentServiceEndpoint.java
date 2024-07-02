package com.wstutorial.ws;

import com.wstutorial.ws.clients.ContentClient;
import com.wstutorial.ws.clients.ManagerClient;
import com.wstutorial.ws.clients.ManagerClient;

import com.wstutorial.ws.commonservice.StatusCodeResponse;
import com.wstutorial.ws.contentservice.CreateContentRequest;
import com.wstutorial.ws.contentservice.GetContentsRequest;
import com.wstutorial.ws.contentservice.GetContentsResponse;
import com.wstutorial.ws.managecontentservice.*;
import com.wstutorial.ws.managementservice.GetManagerRequest;
import com.wstutorial.ws.managementservice.GetManagerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class ManageContentServiceEndpoint {
    private static final String NAMESPACE_URI = "http://www.wstutorial.com/ws/ManageContentService";
   // private static final List<Manager> managers = new ArrayList<>();
    @Autowired
    ContentClient contentClient;

    @PayloadRoot(namespace = "http://www.wstutorial.com/ws/ContentService", localPart = "getContentsRequest")
    @ResponsePayload
    public GetContentsResponse getContentsRequest(@RequestPayload GetContentsRequest request) throws Exception {
        GetContentsResponse response = new GetContentsResponse();
        GetContentsRequest getContentsRequest = new GetContentsRequest();
        return contentClient.getContents(getContentsRequest);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ManageCreateContent")
    @ResponsePayload
    public StatusCodeResponse manageContentType(@RequestPayload ManageContentType request) throws Exception {
        StatusCodeResponse response = new StatusCodeResponse();

        CreateContentRequest createContentRequest = new CreateContentRequest();

        // Map the fields from ManageContentType to CreateContentRequest
        createContentRequest.setContentType(request.getContent());
        contentClient.createContent(createContentRequest);
        response.setStatusCode("201 Created");
        return response;
    }


//    @PayloadRoot(namespace = "http://www.wstutorial.com/ws/ContentService", localPart = "getContentsRequest")
//    @ResponsePayload
//    public GetManagerResponse getManagerResponse(@RequestPayload GetManagerRequest request) throws Exception {
//        GetManagerResponse response = new GetManagerResponse();
//        GetManagerRequest getManagerRequest = new GetManagerRequest();
//        return ManagerClient
//    }


}

