package com.houserenting.controller;

import com.houserenting.entity.Customer;
import com.houserenting.service.CustomerService;
import com.houserenting.utils.MsgMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/customer",method = RequestMethod.POST)
public class CustomerController {
    @Autowired
    CustomerService service;

    @RequestMapping(value = "/confirm")
    public Map<String, Object> confirm(@RequestBody Map o) {
        boolean result = service.confirm((String)o.get("username"));
        MsgMap msg = new MsgMap();
        if(!result)
            msg.putSuccessCode();
        else
            msg.putFailedCode("user exists");
        return msg;
    }

    @RequestMapping(value = "/signup")
    public Map<String,Object> signup(@RequestBody Customer customer){
        boolean result = service.signup(customer);

        MsgMap msg = new MsgMap();
        if(result){
            msg.putSuccessCode();
            msg.put("user", customer);
        }else
            msg.putFailedCode("signUpFailed");

        return msg;
    }

    @RequestMapping(value = "/login")
    public Map<String,Object> login(@RequestBody Map o){
        String username = (String)o.get("username");
        String password = (String)o.get("password");
        Customer customer = service.login(username,password);

        MsgMap msg = new MsgMap();
        if(customer != null){
            msg.putSuccessCode();
            msg.put("user",customer);
        }else
            msg.putFailedCode("loginFailed");

        return msg;
    }

    @RequestMapping("/updateInfo")
    public Map<String, Object> updateInfo(@RequestBody Customer customer){
        Customer newCustomer = service.getCustomer(customer.getCid());

        newCustomer.setSex(customer.getSex());
        newCustomer.setTel(customer.getTel());
        newCustomer.setAge(customer.getAge());
        newCustomer.setRealname(customer.getRealname());
        newCustomer.setAddress(customer.getAddress());

        service.updateInfo(newCustomer);

        MsgMap msg = new MsgMap();
        msg.putSuccessCode();
        msg.put("user",newCustomer);

        return msg;

    }

    @RequestMapping("/change")
    public Map<String,Object> change(@RequestBody Map o){
        String username = (String)o.get("username");
        String tel = (String)o.get("tel");
        String password = (String)o.get("password");
        String newPassword = (String)o.get("newPassword");
        boolean result = service.change(username, tel, password, newPassword);

        MsgMap msg = new MsgMap();
        if(result){
            msg.putSuccessCode();
        }else
            msg.putFailedCode("loginFailed");

        return msg;
    }
}
