package com.houserenting.controller;

import com.houserenting.entity.Customer;
import com.houserenting.service.CustomerService;
import com.houserenting.utils.MsgMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/customer",method = RequestMethod.POST)
public class CustomerController {
    @Autowired
    CustomerService service;

    @RequestMapping(value = "/confirm")
    public Map<String, Object> confirm(String username) {
        boolean result = service.confirm(username);
        MsgMap msg = new MsgMap();
        if(!result)
            msg.putSuccessCode();
        else
            msg.putFailedCode("user exists");
        return msg;
    }

    @RequestMapping(value = "/signup")
    public Map<String,Object> signup(Customer customer){
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
    public Map<String,Object> login(String username, String password){
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
    public Map<String, Object> updateInfo(Customer customer){
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
