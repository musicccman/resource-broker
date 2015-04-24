package com.resourcebroker.rabbitmq.request;

import com.resourcebroker.common.config.RabbitMQConfig;
import com.resourcebroker.common.entitiy.ServiceUserEntity;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Andrey Kozlov
 */
@Component
public class RequestBuilder {

    @Autowired
    private RabbitMQConfig rabbitMQConfig;

    public HttpRequestBase getVhostCreateRequest(String hostName) {
        String url = rabbitMQConfig.getRabbitMQUrl() + APIUrlBuilder.getVhostsCreateURL(hostName);
        HttpPut httpPut = (HttpPut) createRequest(RequestType.PUT, url);
        StringEntity entity = HttpEntityBuilder.createBulder().addKeyValuePare("name", hostName).createEntity();
        httpPut.setEntity(entity);
        return httpPut;
    }

    public HttpRequestBase getServiceUserCreateRequest(ServiceUserEntity serviceUser) {
        String url = rabbitMQConfig.getRabbitMQUrl() + APIUrlBuilder.getUsersCreateURL(serviceUser.getUserName());
        HttpPut httpPut = (HttpPut) createRequest(RequestType.PUT, url);
        StringEntity entity = HttpEntityBuilder.createBulder()
                .addKeyValuePare("username", serviceUser.getUserName())
                .addKeyValuePare("has-password", "true")
                .addKeyValuePare("password", serviceUser.getUserPassword())
                .addKeyValuePare("tags", "management")
                .createEntity();
        httpPut.setEntity(entity);
        return httpPut;
    }

    public HttpRequestBase getUserToVhostBindingRequest(ServiceUserEntity serviceUser, String serviceName) {
        String url = rabbitMQConfig.getRabbitMQUrl() + APIUrlBuilder.getUserToVhostAddBindingURL(serviceUser.getUserName(), serviceName);
        HttpPut httpPut = (HttpPut) createRequest(RequestType.PUT, url);
        StringEntity entity = HttpEntityBuilder.createBulder()
                .addKeyValuePare("username", serviceUser.getUserName())
                .addKeyValuePare("vhost", serviceName)
                .addKeyValuePare("configure", ".*")
                .addKeyValuePare("write", ".*")
                .addKeyValuePare("read", ".*")
                .createEntity();
        httpPut.setEntity(entity);
        return httpPut;
    }

    public HttpRequestBase getVhostDeleteRequest(String hostName) {
        String url = rabbitMQConfig.getRabbitMQUrl() + APIUrlBuilder.getVhostsDeleteURL(hostName);
        HttpDelete httpDelete = (HttpDelete) createRequest(RequestType.DELETE, url);
        return httpDelete;
    }

    public HttpRequestBase getServiceUserDeleteRequest(ServiceUserEntity serviceUserEntity){
        String url = rabbitMQConfig.getRabbitMQUrl() + APIUrlBuilder.getUsersDeleteURL(serviceUserEntity.getUserName());
        HttpDelete httpDelete = (HttpDelete) createRequest(RequestType.DELETE, url);
        return httpDelete;
    }

    private HttpRequestBase createRequest(RequestType requestType, String url){
        HttpRequestBase requestBase = null;
        switch (requestType) {
            case PUT:
                requestBase = new HttpPut(url);
                break;
            case DELETE:
                requestBase = new HttpDelete(url);
                break;
            case GET:
                requestBase = new HttpGet(url);
                break;
        }
        requestBase.setHeader("Authorization", "Basic " + createAuthToken());
        requestBase.setHeader("Content-Type", "application/json");
        return requestBase;
    }

    private String createAuthToken() {
        return new String(Base64.encodeBase64((rabbitMQConfig.getRabbitMQAdminName() + ":" + rabbitMQConfig.getRabbitMQAdminPass()).getBytes()) );
    }

    enum RequestType{
        GET, PUT, DELETE
    }

}
