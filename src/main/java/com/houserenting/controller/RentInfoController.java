package com.houserenting.controller;

import com.houserenting.entity.RentInfo;
import com.houserenting.service.CustomerService;
import com.houserenting.service.RentInfoService;
import com.houserenting.utils.MsgMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/rentinfo",method = RequestMethod.POST)
public class RentInfoController {
    @Autowired
    RentInfoService rentInfoService;

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/addRentInfo")
    public Map<String, Object> addRentInfo(@RequestBody RentInfo rentInfo){
        boolean result = rentInfoService.addRentInfo(rentInfo);
        MsgMap msg = new MsgMap();
        if(result){
            msg.putSuccessCode();
            msg.put("rentinfo", rentInfo);
        }
        else
            msg.putFailedCode("addRentInfo failed");
        return msg;
    }
}
