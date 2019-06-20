package com.houserenting.controller;

import com.houserenting.entity.Costumer;
import com.houserenting.service.CostumerService;
import com.houserenting.utils.MsgMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/costumer",method = RequestMethod.POST)
public class CostumerController {
    @Autowired
    CostumerService service;

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
    public Map<String,Object> signup(Costumer costumer){
        boolean result = service.signup(costumer);

        MsgMap msg = new MsgMap();
        if(result){
            msg.putSuccessCode();
            msg.put("user", costumer);
        }else
            msg.putFailedCode("signUpFailed");

        return msg;
    }

    @RequestMapping(value = "/login")
    public Map<String,Object> login(String username, String password){
        Costumer costumer = service.login(username,password);

        MsgMap msg = new MsgMap();
        if(costumer != null){
            msg.putSuccessCode();
            msg.put("user",costumer);
        }else
            msg.putFailedCode("loginFailed");

        return msg;
    }

    @RequestMapping("/updateInfo")
    public Map<String, Object> updateInfo(Costumer costumer){
        Costumer newCostumer = service.getCostumer(costumer.getCid());

        newCostumer.setSex(costumer.getSex());
        newCostumer.setTel(costumer.getTel());
        newCostumer.setAge(costumer.getAge());
        newCostumer.setRealname(costumer.getRealname());
        newCostumer.setAddress(costumer.getAddress());

        service.updateInfo(newCostumer);

        MsgMap msg = new MsgMap();
        msg.putSuccessCode();
        msg.put("user",newCostumer);

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
