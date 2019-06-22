package com.houserenting.controller;

import com.houserenting.entity.Admin;
import com.houserenting.service.AdminService;
import com.houserenting.utils.MsgMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/admin",method = RequestMethod.POST)
public class AdminController {
    private final AdminService service;

    @Autowired
    public AdminController(AdminService service) {
        this.service = service;
    }

    @RequestMapping("/confirm")
    public Map<String,Object> confirm(@RequestBody Map o){
        boolean result = service.confirm((String)o.get("username"));
        MsgMap msg = new MsgMap();
        if(!result)
            msg.putSuccessCode();
        else
            msg.putFailedCode("user exists");
        return msg;
    }

    @RequestMapping(value = "/signup")
    public Map<String,Object> signup(@RequestBody Admin admin){
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
    public Map<String,Object> login(@RequestBody Map o){
        String username = (String)o.get("username");
        String password = (String)o.get("password");
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
    public Map<String,Object> change(@RequestBody Map o){
        String username = (String)o.get("username");
        String tel = (String)o.get("tel");
        String password = (String)o.get("password");
        String newPassword = (String)o.get("newpassword");

        boolean result = service.change(username, tel, password, newPassword);

        MsgMap msg = new MsgMap();
        if(result){
            msg.putSuccessCode();
        }else
            msg.putFailedCode("changeFailed");

        return msg;
    }
}
