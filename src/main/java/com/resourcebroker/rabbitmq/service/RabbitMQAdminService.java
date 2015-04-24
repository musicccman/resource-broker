package com.resourcebroker.rabbitmq.service;

import com.resourcebroker.common.entitiy.ServiceInstanceEntity;
import com.resourcebroker.common.entitiy.ServiceUserEntity;
import com.resourcebroker.common.repository.ServiceInstanceRepository;
import com.resourcebroker.common.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * @author: Andrey Kozlov
 */
@Service
public class RabbitMQAdminService implements AdminService {

    @Autowired
    @Qualifier("rabbitMQRepository")
    private ServiceInstanceRepository serviceInstanceRepository;

    @Override
    public void createServiceInstance(ServiceInstanceEntity serviceInstance) throws UnsupportedEncodingException {
        String instanceName = serviceInstanceRepository.createService(serviceInstance.getServiceName());
        ServiceUserEntity user = serviceInstanceRepository.createServiceUser(serviceInstance.getServiceUser());
        serviceInstanceRepository.addUserToServiceInstance(user, instanceName);

    }

    @Override
    public void deleteServiceInstance(ServiceInstanceEntity serviceInstance) {
        serviceInstanceRepository.deleteService(serviceInstance.getServiceName());
        serviceInstanceRepository.deleteServiceUser(serviceInstance.getServiceUser());
    }
}
