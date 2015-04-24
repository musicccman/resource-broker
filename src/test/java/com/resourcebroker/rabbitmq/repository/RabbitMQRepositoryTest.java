package com.resourcebroker.rabbitmq.repository;

import com.resourcebroker.common.repository.ServiceInstanceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:rabbitmq-broker-test-context.xml")
public class RabbitMQRepositoryTest {

    @Autowired
    @Qualifier("rabbitMQRepository")
    ServiceInstanceRepository serviceInstanceRepository;

    @Test
    public void testCreateService() throws Exception {
        serviceInstanceRepository.createService("testVHOST");
    }

    public void testCreateServiceUser() throws Exception {

    }

    public void testAddUserToServiceInstance() throws Exception {

    }

    public void testDeleteService() throws Exception {

    }

    public void testDeleteServiceUser() throws Exception {

    }
}