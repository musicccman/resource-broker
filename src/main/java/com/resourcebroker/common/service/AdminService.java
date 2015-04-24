package com.resourcebroker.common.service;

import com.resourcebroker.common.entitiy.ServiceInstanceEntity;

import java.io.UnsupportedEncodingException;

/**
 * @author: Andrey Kozlov
 */
public interface AdminService {

    void createServiceInstance(ServiceInstanceEntity serviceInstance) throws UnsupportedEncodingException;

    void deleteServiceInstance(ServiceInstanceEntity serviceInstance);

}
