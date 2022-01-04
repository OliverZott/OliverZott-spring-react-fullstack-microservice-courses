package com.velo.microserviceusermanagement.intercomm;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

// used to connect to user-service
@FeignClient("user-service")
public interface UserClient {


    // send post request to user-service to get user-names according to ID-List
    //      see "getNamesOfUsers()" in "user-service"
    //      later we can call it from spring controller class
    @RequestMapping(method = RequestMethod.POST, value = "/service/names", consumes = "application/json")
    List<String> getUserNames(@RequestBody List<Long> idList);

}
    