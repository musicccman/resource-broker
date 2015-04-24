package com.resourcebroker.rabbitmq.repository;

import com.resourcebroker.common.entitiy.ServiceUserEntity;
import com.resourcebroker.common.repository.ServiceInstanceRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:rabbitmq-broker-test-context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RabbitMQRepositoryTest {

    @Autowired
    @Qualifier("rabbitMQRepository")
    ServiceInstanceRepository serviceInstanceRepository;

    public String serviceName = "junitTestHost";
    public ServiceUserEntity serviceUserEntity = new ServiceUserEntity("junitUser", "111");

    @Test
    public void testCreateService() throws Exception {
        serviceInstanceRepository.createService(serviceName);
    }

    @Test
    public void testCreateServiceUser() throws Exception {
        serviceInstanceRepository.createServiceUser(serviceUserEntity);
    }

    @Test
    public void testAddUserToServiceInstance() throws Exception {
        serviceInstanceRepository.addUserToServiceInstance(serviceUserEntity, serviceName);
    }

    public void testDeleteService() throws Exception {

    }

    public void testDeleteServiceUser() throws Exception {

    }

}