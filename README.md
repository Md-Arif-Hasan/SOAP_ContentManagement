# Content Management System with SOA Principles

## Overview

This Content Management System is a SOAP-based application designed for efficient content creation, management, and analysis. The application facilitates a smooth content workflow where users can create and manage content, while managers oversee the process and gain insights. The architecture follows Service-Oriented Architecture (SOA) principles, ensuring schema centralization, loose coupling, and service reusability.

## Services

### Entity Services
- **User Service**: Manages user information and operations.
- **Content Service**: Handles content creation, updates, and queries.
- **Manager Service**: Manages admin or moderator information and operations.
- **Blog Service**: Manages blog-specific content and operations.

### Utility Services
- **Logging Service**: Records log messages for various events.
- **Content Insights Service**: Provides analytics and insights for content performance.

### Task Services
- **Manage Content Service**: Orchestrates other services to manage the content workflow.

### Common Service
- **Common Service**: Contains common schemas used across the system.



## How to Install

To install and run the Content Management System, follow these steps:

1. **Clone the Repository**:
- git clone https://github.com/Md-Arif-Hasan/SOAP_ContentManagement.git
- cd SOAP_ContentManagement

2. **Load Each Service into Your IDE**:
- Open your preferred IDE (e.g., IntelliJ IDEA).
- Load each folder (SOAP_Users, SOAP_Contents, SOAP_ManageContent etc.) as distinct projects.

3. **Run the Projects**:
- For each loaded project, configure the run configurations if necessary.
- Run each project to start the respective service.

Note: Ensure that the required ports are free on your system before running the services.

## Content Management Service Workflow

1. **User Operations**:
   - Users can perform CRUD (Create, Read, Update, Delete) operations on blogs through the Blog Service.
   - Users can read content created by Managers through the Content Service.
   - All user actions are logged using the Logging Service.

2. **Manager Operations**:
   - Managers can perform CRUD operations on specific content that is only accessible to managers, using the Content Service.
   - Managers can review and approve content.
   - Upon content approval:
     - The content status is updated.
     - A log message is generated for the content approval using the Logging Service.

3. **Content Management**:
   - The Manage Content Task Service (implemented in ContentClient.java) orchestrates the content management process.
   - It interacts with other services to manage the content lifecycle.

4. **Logging**:
   - The Logging Service (implemented in LoggerClient.java) records all significant events across the system.
   - Log messages are generated for actions such as content creation, updates, and approvals.

5. **Management Overview**:
   - The Management Service (implemented in ManagerClient.java) provides an interface for managers to oversee the system.
   - Managers can view analytics and insights about content performance through this service.

6. **Content Analysis**:
   - The Content Insights Service analyzes content performance and usage.
   - This data is made available to managers through the Management Service.


7. **Common Service Integration**:
   - All services utilize the Common Service for shared schemas and standardized responses.
   - The Common Service provides elements like 'fault' and 'StatusCodeResponse' that are used across the system.

8. **Service Interaction**:
   - Services communicate with each other using SOAP protocols.
   - The ManageContent service acts as an orchestrator, coordinating between User, Content, Blog, and Logging services as needed.


