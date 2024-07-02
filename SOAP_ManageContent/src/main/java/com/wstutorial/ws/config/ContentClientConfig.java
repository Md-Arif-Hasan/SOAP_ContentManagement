package com.wstutorial.ws.config;

import com.wstutorial.ws.clients.ContentClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ContentClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPaths( "com.wstutorial.ws.commonservice", "com.wstutorial.ws.contentservice", "com.wstutorial.ws.logservice",  "com.wstutorial.ws.userservice");
        return marshaller;
    }

    @Bean
    public ContentClient contentClient(Jaxb2Marshaller marshaller) {
        ContentClient client = new ContentClient();
        client.setDefaultUri("http://localhost:8090/ws/ContentService");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
