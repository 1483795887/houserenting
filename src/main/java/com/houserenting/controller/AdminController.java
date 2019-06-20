package com.houserenting.controller;

import com.houserenting.entity.Admin;
import com.houserenting.service.AdminService;
import com.houserenting.utils.MsgMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/admin",method = RequestMethod.POST)
public class AdminController {
    @Autowired
    AdminService service;

    @RequestMapping("/confirm")
    public Map<String,Object> confirm(@RequestParam(value = "username") String username){
        boolean result = service.confirm(username);
        MsgMap msg = new MsgMap();
        if(!result)
            msg.putSuccessCode();
        else
            msg.putFailedCode("user exists");
        return msg;
    }

    @RequestMapping(value = "/signup")
    public Map<String,Object> signup(Admin admin){
        boolean result = service.signup(admin);

        MsgMap msg = new MsgMap();
        if(result){
            msg.putSuccessCode();
            msg.put("user", admin);
        }else
            msg.putFailedCode("signUpFailed");

        return msg;
    }

    @RequestMapping(value = "/login")
    public Map<String,Object> login(String username, String password){
        Admin admin = service.login(username,password);

        MsgMap msg = new MsgMap();
        if(admin != null){
            msg.putSuccessCode();
            msg.put("user",admin);
        }else
            msg.putFailedCode("loginFailed");

        return msg;
    }

    @RequestMapping("/change")
    public Map<String,Object> change(String username, String tel, String password, String newPassword){
        boolean result = service.change(username, tel, password, newPassword);

        MsgMap msg = new MsgMap();
        if(result){
            msg.putSuccessCode();
        }else
            msg.putFailedCode("loginFailed");

        return msg;
    }
}
