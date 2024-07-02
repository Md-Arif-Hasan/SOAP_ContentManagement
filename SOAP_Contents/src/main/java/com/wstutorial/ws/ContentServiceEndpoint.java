package com.wstutorial.ws;

//import com.wstutorial.ws.commonservice.StatusCode;
import com.wstutorial.ws.commonservice.StatusCodeResponse;
import com.wstutorial.ws.contentservice.*;
import com.wstutorial.ws.contentservice.Content;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class ContentServiceEndpoint {
	private static final String NAMESPACE_URI = "http://www.wstutorial.com/ws/ContentService";
	private static final List<Content> contents = new ArrayList<>();

	// Initialize with some dummy data
	static {
		Content content1 = new Content();
		content1.setSerialNo(1L);
		content1.setHeadline("INDIA WC CHAMPIONS");
		content1.setDescription("Ind beats SA by 7 runs");

		Content content2 = new Content();
		content2.setSerialNo(2L);
		content2.setHeadline("IND VS SA");
		content2.setDescription("Final match: full of excitement and thrill. Finally India won the title");

		contents.add(content1);
		contents.add(content2);
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateContentRequest")
	@ResponsePayload
	public StatusCodeResponse updateContent(@RequestPayload UpdateContentRequest request) throws Exception {
		StatusCodeResponse response = new StatusCodeResponse();
		Content updatedContent = updateContentInDatabase(request.getContentType());
        response.setStatusCode("200 OK");
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createContentRequest")
	@ResponsePayload
	public StatusCodeResponse createContent(@RequestPayload CreateContentRequest request) throws Exception {
		StatusCodeResponse response = new StatusCodeResponse();
		Content newContent = createNewContentInDatabase(request.getContentType());
		response.setStatusCode("201 CREATED");
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteContentRequest")
	@ResponsePayload
	public StatusCodeResponse deleteContent(@RequestPayload DeleteContentRequest request) throws Exception {
		StatusCodeResponse response = new StatusCodeResponse();

		boolean success = deleteContentFromDatabase(request.getId());
		response.setStatusCode("200 SUCCESS");
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getContentRequest")
	@ResponsePayload
	public GetContentResponse getContent(@RequestPayload GetContentRequest request) throws Exception {
		ObjectFactory factory = new ObjectFactory();
		GetContentResponse response = factory.createGetContentResponse();
		Content content = getContentBySerialNo(request.getId());
		response.setContent(content);

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getContentsRequest")
	@ResponsePayload
	public GetContentsResponse getContents(@RequestPayload GetContentsRequest request) throws Exception {
		ObjectFactory factory = new ObjectFactory();
		GetContentsResponse response = factory.createGetContentsResponse();
		List<Content> allContents = getAllContents();
		response.getContents().addAll(allContents);

		return response;
	}

	private Content updateContentInDatabase(Content content) {
		for (int i = 0; i < contents.size(); i++) {
			if (contents.get(i).getSerialNo() == content.getSerialNo()) {
				contents.set(i, content);
				return content;
			}
		}
		return null; // Return null if content not found
	}

	private Content createNewContentInDatabase(Content content) {
		content.setSerialNo(generateUniqueSerialNo());
		contents.add(content);
		return content;
	}

	private boolean deleteContentFromDatabase(Long contentSerialNo) {
		return contents.removeIf(content -> content.getSerialNo() == contentSerialNo);
	}

	private Content getContentBySerialNo(Long contentSerialNo) {
		for (Content content : contents) {
			if (content.getSerialNo() == contentSerialNo) {
				return content;
			}
		}
		return null; // Return null if content not found
	}

	private List<Content> getAllContents() {
		return new ArrayList<>(contents);
	}

	// Dummy method for generating unique IDs
	private Long generateUniqueSerialNo() {
		return System.currentTimeMillis();
	}
}
