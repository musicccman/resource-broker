package com.resourcebroker.rabbitmq.repository;

import com.resourcebroker.common.config.RabbitMQConfig;
import com.resourcebroker.common.entitiy.ServiceUserEntity;
import com.resourcebroker.common.repository.ServiceInstanceRepository;
import com.resourcebroker.rabbitmq.request.RequestBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author: Andrey Kozlov
 */
@Repository("rabbitMQRepository")
public class RabbitMQRepository implements ServiceInstanceRepository {

    Logger logger = Logger.getRootLogger();

    @Autowired
    private RabbitMQConfig rabbitMQConfig;
    @Autowired
    private RequestBuilder requestBuilder;

    private CloseableHttpClient httpClient;

    @PostConstruct
    void initHttpClient(){
        httpClient = HttpClients.createDefault();
    }

    @Override
    public String createService(String serviceName) throws UnsupportedEncodingException {

        HttpRequestBase requestBase = requestBuilder.getVhostCreateRequest(serviceName);
        try (CloseableHttpResponse response = httpClient.execute(requestBase)) {
            logger.info("vhost created: " + serviceName);
            return serviceName;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ServiceUserEntity createServiceUser(ServiceUserEntity serviceUser) {

        return null;
    }

    @Override
    public void addUserToServiceInstance(ServiceUserEntity serviceUser, String serviceName) {

    }

    @Override
    public void deleteService(String serviceName) {

    }

    @Override
    public void deleteServiceUser(ServiceUserEntity serviceUser) {

    }

}
