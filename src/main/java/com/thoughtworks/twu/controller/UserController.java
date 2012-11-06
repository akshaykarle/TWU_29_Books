package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String redirect(HttpServletRequest httpServletRequest) {
        String remoteUser = httpServletRequest.getRemoteUser();
        if(userService.isUserExisted(remoteUser))
            return "/welcome";
        else
            return "/create-user-profile";
    }
}