package com.example.university_register2.clientproxy;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientProxyConfig {

    @Value("${students.api.url.v1}")
    private String studentsEndpointUrl;

    @Bean
    public StudentResourceV1 getStudentResourceV1() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(studentsEndpointUrl);
        StudentResourceV1 proxy = target.proxy(StudentResourceV1.class);
        return proxy;
    }
}