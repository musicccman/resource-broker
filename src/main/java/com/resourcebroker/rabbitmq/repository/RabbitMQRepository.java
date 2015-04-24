package com.resourcebroker.rabbitmq.repository;

import com.resourcebroker.common.entitiy.ServiceUserEntity;
import com.resourcebroker.common.exception.ApplicationException;
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

/**
 * @author: Andrey Kozlov
 */
@Repository("rabbitMQRepository")
public class RabbitMQRepository implements ServiceInstanceRepository {

    Logger logger = Logger.getRootLogger();

    @Autowired
    private RequestBuilder requestBuilder;

    private CloseableHttpClient httpClient;

    @PostConstruct
    void initHttpClient(){
        httpClient = HttpClients.createDefault();
    }

    @Override
    public String createService(String serviceName) {
        HttpRequestBase requestBase = requestBuilder.getVhostCreateRequest(serviceName);
        executeRequest(requestBase);
        return serviceName;
    }

    @Override
    public ServiceUserEntity createServiceUser(ServiceUserEntity serviceUser) {
        HttpRequestBase requestBase = requestBuilder.getServiceUserCreateRequest(serviceUser);
        executeRequest(requestBase);
        return serviceUser;
    }

    @Override
    public void addUserToServiceInstance(ServiceUserEntity serviceUser, String serviceName) {
        HttpRequestBase requestBase = requestBuilder.getUserToVhostBindingRequest(serviceUser, serviceName);
        executeRequest(requestBase);
    }

    @Override
    public void deleteService(String serviceName) {
        HttpRequestBase requestBase = requestBuilder.getVhostDeleteRequest(serviceName);
        executeRequest(requestBase);
    }

    @Override
    public void deleteServiceUser(ServiceUserEntity serviceUser) {
        HttpRequestBase requestBase = requestBuilder.getServiceUserDeleteRequest(serviceUser);
        executeRequest(requestBase);
    }

    private void executeRequest(HttpRequestBase requestBase) throws ApplicationException{
        try (CloseableHttpResponse response = httpClient.execute(requestBase)) {
            handleResponce(response);
        } catch (IOException e) {
            ApplicationException.fail(e);
        }
    }

    private void handleResponce(CloseableHttpResponse response) throws ApplicationException{
        int responceCode = response.getStatusLine().getStatusCode();
        if (!((responceCode>199)&&(responceCode<299))){ //not 2.. (sucess responce)
            ApplicationException.fail(response.getStatusLine().toString());
        }
    }

}
