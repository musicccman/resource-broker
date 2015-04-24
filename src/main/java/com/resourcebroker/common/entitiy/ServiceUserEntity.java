package com.resourcebroker.common.entitiy;

/**
 * @author: Andrey Kozlov
 */
public class ServiceUserEntity {

    public ServiceUserEntity() {
    }

    public ServiceUserEntity(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    private String userName;
    private String userPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
