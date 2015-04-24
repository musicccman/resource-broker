package com.resourcebroker.rabbitmq.request;

/**
 * @author: Andrey Kozlov
 */
public class APIUrlBuilder {

    private static String USERS = "/api/users";
    private static String VHOSTS = "/api/vhosts";
    private static String PERMISSIONS = "/api/permissions";

    public static String getUsersGetURL(){
        return USERS;
    }

    public static String getUsersCreateURL(String userName){
        return USERS + "/" + userName;
    }

    public static String getUsersDeleteURL(String userName){
        return USERS + "/" + userName;
    }

    public static String getVhostsGetURL(){
        return VHOSTS;
    }

    public static String getVhostsCreateURL(String hostName){
        return VHOSTS + "/%2F" + hostName;
    }

    public static String getVhostsDeleteURL(String hostName){
        return VHOSTS + "/%2F" + hostName;
    }

    public static String getUserToVhostAddBindingURL(String username, String hostName){
        return PERMISSIONS + "/%2F" + hostName + "/" + username;
    }

    public static String getUserToVhostDeleteBindingURL(String username, String hostName){
        return PERMISSIONS + "/%2F" + hostName + "/" + username;
    }

}
