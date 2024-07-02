package com.wstutorial.ws;

import com.wstutorial.ws.commonservice.StatusCodeResponse;
import com.wstutorial.ws.managementservice.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class ManagementServiceEndpoint {
	private static final String NAMESPACE_URI = "http://www.wstutorial.com/ws/ManagementService";
	private static final List<Manager> managers = new ArrayList<>();


	static {
		Manager manager1 = new Manager();
		manager1.setId(1L);
		manager1.setName("John Doe");
		manager1.setEmail("john.doe@example.com");
		manager1.setPhoneNumber("01712331243");

		Manager manager2 = new Manager();
		manager2.setId(2L);
		manager2.setName("Jane Smith");
		manager2.setEmail("jane.smith@example.com");
		manager2.setPhoneNumber("01705435434");

		managers.add(manager1);
		managers.add(manager2);
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateManagerRequest")
	@ResponsePayload
	public StatusCodeResponse updateManager(@RequestPayload UpdateManagerRequest request) throws Exception {
		StatusCodeResponse response = new StatusCodeResponse();
		Manager updatedManager = updateManagerInDatabase(request.getManagerType());
		response.setStatusCode("200 OK");
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createManagerRequest")
	@ResponsePayload
	public StatusCodeResponse createManager(@RequestPayload CreateManagerRequest request) throws Exception {
		StatusCodeResponse response = new StatusCodeResponse();
		Manager newManager = createNewManagerInDatabase(request.getManagerType());
		response.setStatusCode("201 CREATED");
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteManagerRequest")
	@ResponsePayload
	public StatusCodeResponse deleteManager(@RequestPayload DeleteManagerRequest request) throws Exception {
		StatusCodeResponse response = new StatusCodeResponse();

		boolean success = deleteManagerFromDatabase(request.getId());
		response.setStatusCode(success ? "success" : "failed");
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getManagerRequest")
	@ResponsePayload
	public GetManagerResponse getManager(@RequestPayload GetManagerRequest request) throws Exception {
		ObjectFactory factory = new ObjectFactory();
		GetManagerResponse response = factory.createGetManagerResponse();
		Manager manager = getManagerById(request.getId());
		response.setManager(manager);

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getManagersRequest")
	@ResponsePayload
	public GetManagersResponse getManagers(@RequestPayload GetManagersRequest request) throws Exception {
		ObjectFactory factory = new ObjectFactory();
		GetManagersResponse response = factory.createGetManagersResponse();
		List<Manager> allManagers = getAllManagers();
		response.getManagers().addAll(allManagers);

		return response;
	}

	private Manager updateManagerInDatabase(Manager manager) {
		for (int i = 0; i < managers.size(); i++) {
			if (managers.get(i).getId() == manager.getId()) {
				managers.set(i, manager);
				return manager;
			}
		}
		return null;
	}

	private Manager createNewManagerInDatabase(Manager manager) {
		manager.setId(generateUniqueId());
		managers.add(manager);
		return manager;
	}

	private boolean deleteManagerFromDatabase(Long managerId) {
		return managers.removeIf(manager -> manager.getId() == managerId);
	}

	private Manager getManagerById(Long managerId) {
		for (Manager manager : managers) {
			if (manager.getId() == managerId) {
				return manager;
			}
		}
		return null;
	}

	private List<Manager> getAllManagers() {
		return new ArrayList<>(managers);
	}


	private Long generateUniqueId() {
		return System.currentTimeMillis();
	}
}
