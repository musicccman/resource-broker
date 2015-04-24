package com.resourcebroker.common.repository;

import com.resourcebroker.common.entitiy.ServiceUserEntity;

import java.io.UnsupportedEncodingException;

/**
 * @author: Andrey Kozlov
 */
public interface ServiceInstanceRepository {

    String createService(String serviceName) throws UnsupportedEncodingException;

    ServiceUserEntity createServiceUser(ServiceUserEntity serviceUser);

    void addUserToServiceInstance(ServiceUserEntity serviceUser, String serviceName);

    void deleteService(String serviceName);

    void deleteServiceUser(ServiceUserEntity serviceUser);

}
