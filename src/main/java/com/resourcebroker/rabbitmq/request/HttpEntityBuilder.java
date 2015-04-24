package com.resourcebroker.rabbitmq.request;

import com.resourcebroker.common.exception.ApplicationException;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author: Andrey Kozlov
 */
public class HttpEntityBuilder {

    private static String startSymbol = "{";
    private static String endSymbol = "}";
    private static String separator = ",";

    private Map<String, String> keyValueMap = new HashMap<>();

    public HttpEntityBuilder addKeyValuePare(String key, String value){
        keyValueMap.put(key, value);
        return this;
    }

    public StringEntity createEntity() {
        String entityValue = createEntityValue();
        StringEntity stringEntity = createStringEntity(entityValue);
        return stringEntity;
    }

    private StringEntity createStringEntity(String entityValue) {
        StringEntity stringEntity = null;
        try {
            stringEntity = new StringEntity(entityValue);
        } catch (UnsupportedEncodingException e) {
            ApplicationException.fail("Can't create Request entity", e);
        }
        return stringEntity;
    }

    private String createEntityValue() {
        StringBuilder builder = new StringBuilder();

        builder.append(startSymbol);
        Iterator<String> iterator = keyValueMap.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            String value = keyValueMap.get(key);
            builder.append("\"").append(key).append("\":\"").append(value).append("\"");
            if (iterator.hasNext()){
                builder.append(separator);
            }
        }
        builder.append(endSymbol);

        return builder.toString();
    }

    public static HttpEntityBuilder createBulder(){
        return new HttpEntityBuilder();
    }

}
