package com.wstutorial.ws.config;

import com.wstutorial.ws.clients.LoggerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


@Configuration
public class ManageClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPaths( "com.wstutorial.ws.commonservice", "com.wstutorial.ws.contentservice", "com.wstutorial.ws.logservice","com.wstutorial.ws.userservice");
        return marshaller;
    }

    @Bean
    public LoggerClient loggerClient(Jaxb2Marshaller marshaller) {
        LoggerClient client = new LoggerClient();
        client.setDefaultUri("http://localhost:8087/ws/ManagementService");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}


