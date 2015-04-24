package com.resourcebroker.common.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author: Andrey Kozlov
 */
@Configuration
@ImportResource("classpath:/rabbitmq-broker-context.xml")
public class RabbitMQConfig {

    Logger logger = Logger.getRootLogger();

    @Value("$service{rabbitMQ.url}")
    private String rabbitMQUrl;

    @Value("$service{rabbitMQ.adminName}")
    private String rabbitMQAdminName;

    @Value("$service{rabbitMQ.adminPass}")
    private String rabbitMQAdminPass;

    public String getRabbitMQUrl() {
        return rabbitMQUrl;
    }

    public void setRabbitMQUrl(String rabbitMQUrl) {
        this.rabbitMQUrl = rabbitMQUrl;
    }

    public String getRabbitMQAdminName() {
        return rabbitMQAdminName;
    }

    public void setRabbitMQAdminName(String rabbitMQAdminName) {
        this.rabbitMQAdminName = rabbitMQAdminName;
    }

    public String getRabbitMQAdminPass() {
        return rabbitMQAdminPass;
    }

    public void setRabbitMQAdminPass(String rabbitMQAdminPass) {
        this.rabbitMQAdminPass = rabbitMQAdminPass;
    }
}
