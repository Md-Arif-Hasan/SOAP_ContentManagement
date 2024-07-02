package com.wstutorial.ws;

//import com.wstutorial.ws.commonservice.StatusCode;
import com.wstutorial.ws.commonservice.StatusCodeResponse;
import com.wstutorial.ws.userservice.*;
import com.wstutorial.ws.userservice.User;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class UserServiceEndpoint {
	private static final String NAMESPACE_URI = "http://www.wstutorial.com/ws/UserService";
	private static final List<User> users = new ArrayList<>();

	// Initialize with some dummy data
	static {
		User user1 = new User();
		user1.setId(1L);
		user1.setUsername("Shartaz Sajid Nahid");
		user1.setEmail("nahid@example.com");

		User user2 = new User();
		user2.setId(2L);
		user2.setUsername("Md Siam");
		user2.setEmail("siam@example.com");

		users.add(user1);
		users.add(user2);
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUserRequest")
	@ResponsePayload
	public StatusCodeResponse updateUser(@RequestPayload UpdateUserRequest request) throws Exception {
		StatusCodeResponse response = new StatusCodeResponse();
		User updatedUser = updateUserInDatabase(request.getUserType());
        response.setStatusCode("200 OK");
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createUserRequest")
	@ResponsePayload
	public StatusCodeResponse createUser(@RequestPayload CreateUserRequest request) throws Exception {
		StatusCodeResponse response = new StatusCodeResponse();
		User newUser = createNewUserInDatabase(request.getUserType());
		response.setStatusCode("201 CREATED");
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUserRequest")
	@ResponsePayload
	public StatusCodeResponse deleteUser(@RequestPayload DeleteUserRequest request) throws Exception {
		StatusCodeResponse response = new StatusCodeResponse();

		boolean success = deleteUserFromDatabase(request.getId());
		response.setStatusCode("200 SUCCESS");
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
	@ResponsePayload
	public GetUserResponse getUser(@RequestPayload GetUserRequest request) throws Exception {
		ObjectFactory factory = new ObjectFactory();
		GetUserResponse response = factory.createGetUserResponse();
		User user = getUserById(request.getId());
		response.setUser(user);

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUsersRequest")
	@ResponsePayload
	public GetUsersResponse getUsers(@RequestPayload GetUsersRequest request) throws Exception {
		ObjectFactory factory = new ObjectFactory();
		GetUsersResponse response = factory.createGetUsersResponse();
		List<User> allUsers = getAllUsers();
		response.getUsers().addAll(allUsers);

		return response;
	}

	private User updateUserInDatabase(User user) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId() == user.getId()) {
				users.set(i, user);
				return user;
			}
		}
		return null; // Return null if user not found
	}

	private User createNewUserInDatabase(User user) {
		user.setId(generateUniqueId());
		users.add(user);
		return user;
	}

	private boolean deleteUserFromDatabase(Long userId) {
		return users.removeIf(user -> user.getId() == userId);
	}

	private User getUserById(Long userId) {
		for (User user : users) {
			if (user.getId() == userId) {
				return user;
			}
		}
		return null; // Return null if user not found
	}

	private List<User> getAllUsers() {
		return new ArrayList<>(users);
	}

	// Dummy method for generating unique IDs
	private Long generateUniqueId() {
		return System.currentTimeMillis();
	}
}
