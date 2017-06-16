package com.landmaster.springboot;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by tdl on 2017/6/2.
 */
@WebService
public interface UserService {
    @WebMethod
    String getName(@WebParam(name = "userId") Long userId);
    @WebMethod
    User getUser(Long userId);
}
