package com.resourcebroker.common.entitiy;

/**
 * @author: Andrey Kozlov
 */
public class ServiceInstanceEntity {

    private String serviceName;
    private ServiceUserEntity serviceUser;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public ServiceUserEntity getServiceUser() {
        return serviceUser;
    }

    public void setServiceUser(ServiceUserEntity serviceUser) {
        this.serviceUser = serviceUser;
    }
}
