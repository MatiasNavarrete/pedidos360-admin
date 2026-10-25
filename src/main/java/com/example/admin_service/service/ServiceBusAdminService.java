package com.example.admin_service.service;

import com.azure.messaging.servicebus.administration.models.CreateQueueOptions;
import com.azure.messaging.servicebus.administration.ServiceBusAdministrationClient;
import com.azure.messaging.servicebus.administration.ServiceBusAdministrationClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class ServiceBusAdminService {

    private final ServiceBusAdministrationClient adminClient;

    public ServiceBusAdminService(
            @Value("${spring.cloud.azure.servicebus.connection-string}") String connectionString) {
        this.adminClient = new ServiceBusAdministrationClientBuilder()
                .connectionString(connectionString)
                .buildClient();
    }

    public void createQueue(String queueName) {
        if (!adminClient.getQueueExists(queueName)) {
            CreateQueueOptions options = new CreateQueueOptions()
                    .setAutoDeleteOnIdle(null)
                    .setDefaultMessageTimeToLive(Duration.ofDays(14));
            adminClient.createQueue(queueName, options);
        } else {
            throw new IllegalArgumentException("La cola '" + queueName + "' ya existe.");
        }
    }

    public void deleteQueue(String queueName) {
        if (adminClient.getQueueExists(queueName)) {
            adminClient.deleteQueue(queueName);
        } else {
            throw new IllegalArgumentException("La cola '" + queueName + "' no existe.");
        }
    }
}
