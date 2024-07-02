package com.wstutorial.ws;

//import com.wstutorial.ws.commonservice.StatusCode;
import com.wstutorial.ws.commonservice.StatusCodeResponse;
import com.wstutorial.ws.blogpostservice.*;
import com.wstutorial.ws.blogpostservice.Blogpost;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class BlogpostServiceEndpoint {
	private static final String NAMESPACE_URI = "http://www.wstutorial.com/ws/BlogpostService";
	private static final List<Blogpost> blogposts = new ArrayList<>();

	// Initialize with some dummy data
	static {
		Blogpost blogpost1 = new Blogpost();
		blogpost1.setBlogNo(1L);
		blogpost1.setBlogHeadline("India Wins the WORLD CUP 2024");
		blogpost1.setBlogDescription("India beats SA today by 7 runs");


		Blogpost blogpost2 = new Blogpost();
		blogpost2.setBlogNo(2L);
		blogpost2.setBlogHeadline("Virat _ Player of the Match");
		blogpost2.setBlogDescription("Virat and Rohit resigns after winning the world cup ");

		blogposts.add(blogpost1);
		blogposts.add(blogpost2);
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateBlogpostRequest")
	@ResponsePayload
	public StatusCodeResponse updateBlogpost(@RequestPayload UpdateBlogpostRequest request) throws Exception {
		StatusCodeResponse response = new StatusCodeResponse();
		Blogpost updatedBlogpost = updateBlogpostInDatabase(request.getBlogpostType());
        response.setStatusCode("200 OK");
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createBlogpostRequest")
	@ResponsePayload
	public StatusCodeResponse createBlogpost(@RequestPayload CreateBlogpostRequest request) throws Exception {
		StatusCodeResponse response = new StatusCodeResponse();
		Blogpost newBlogpost = createNewBlogpostInDatabase(request.getBlogpostType());
		response.setStatusCode("201 CREATED");
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteBlogpostRequest")
	@ResponsePayload
	public StatusCodeResponse deleteBlogpost(@RequestPayload DeleteBlogpostRequest request) throws Exception {
		StatusCodeResponse response = new StatusCodeResponse();

		boolean success = deleteBlogpostFromDatabase(request.getBlogNo());
		response.setStatusCode("200 SUCCESS");
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBlogpostRequest")
	@ResponsePayload
	public GetBlogpostResponse getBlogpost(@RequestPayload GetBlogpostRequest request) throws Exception {
		ObjectFactory factory = new ObjectFactory();
		GetBlogpostResponse response = factory.createGetBlogpostResponse();
		Blogpost blogpost = getBlogpostById(request.getBlogNo());
		response.setBlogpost(blogpost);

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBlogpostsRequest")
	@ResponsePayload
	public GetBlogpostsResponse getBlogposts(@RequestPayload GetBlogpostsRequest request) throws Exception {
		ObjectFactory factory = new ObjectFactory();
		GetBlogpostsResponse response = factory.createGetBlogpostsResponse();
		List<Blogpost> allBlogposts = getAllBlogposts();
		response.getBlogposts().addAll(allBlogposts);

		return response;
	}

	private Blogpost updateBlogpostInDatabase(Blogpost blogpost) {
		for (int i = 0; i < blogposts.size(); i++) {
			if (blogposts.get(i).getBlogNo()== blogpost.getBlogNo()) {
				blogposts.set(i, blogpost);
				return blogpost;
			}
		}
		return null; // Return null if blogpost not found
	}

	private Blogpost createNewBlogpostInDatabase(Blogpost blogpost) {
		blogpost.setBlogNo(generateUniqueId());
		blogposts.add(blogpost);
		return blogpost;
	}

	private boolean deleteBlogpostFromDatabase(Long blogpostId) {
		return blogposts.removeIf(blogpost -> blogpost.getBlogNo() == blogpostId);
	}

	private Blogpost getBlogpostById(Long blogpostId) {
		for (Blogpost blogpost : blogposts) {
			if (blogpost.getBlogNo() == blogpostId) {
				return blogpost;
			}
		}
		return null; // Return null if blogpost not found
	}

	private List<Blogpost> getAllBlogposts() {
		return new ArrayList<>(blogposts);
	}

	// Dummy method for generating unique IDs
	private Long generateUniqueId() {
		return System.currentTimeMillis();
	}
}
