package com.springboot.elastic.user.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.springboot.elastic.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/aggByAge")
    public String aggTest(){
        Gson gson = new GsonBuilder().create();
        return gson.toJson(userService.aggRangeByAge());
    }
}
