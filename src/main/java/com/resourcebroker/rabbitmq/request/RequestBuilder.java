package com.resourcebroker.rabbitmq.request;

import com.resourcebroker.common.config.RabbitMQConfig;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @author: Andrey Kozlov
 */
@Component
public class RequestBuilder {

    @Autowired
    private RabbitMQConfig rabbitMQConfig;

    public HttpRequestBase getVhostCreateRequest(String hostName) throws UnsupportedEncodingException {
        String url = rabbitMQConfig.getRabbitMQUrl() + APIUrlBuilder.getVhostsCreateURL(hostName);
        HttpPut httpPut = (HttpPut) createRequest(RequestType.PUT, url);
        httpPut.setEntity( new StringEntity( "{\"name\":\""+ hostName +"\"}" ) );
        return httpPut;
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
