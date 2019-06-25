package com.houserenting.controller;

import com.houserenting.entity.Customer;
import com.houserenting.entity.Message;
import com.houserenting.entity.RentInfo;
import com.houserenting.service.CustomerService;
import com.houserenting.service.MessageService;
import com.houserenting.service.RentInfoService;
import com.houserenting.utils.Limit;
import com.houserenting.utils.MsgMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/rentinfo", method = RequestMethod.POST)
public class RentInfoController {
    private final RentInfoService rentInfoService;

    private final CustomerService customerService;

    private final MessageService messageService;

    @Autowired
    public RentInfoController(RentInfoService rentInfoService,
                              CustomerService customerService,
                              MessageService messageService) {
        this.rentInfoService = rentInfoService;
        this.customerService = customerService;
        this.messageService = messageService;
    }

    @RequestMapping(value = "/add")
    public Map<String, Object> addRentInfo(@RequestBody RentInfo rentInfo) {
        boolean result = rentInfoService.addRentInfo(rentInfo);
        MsgMap msg = new MsgMap();
        if (result) {
            msg.putSuccessCode();
            msg.put("rentinfo", rentInfo);
        } else
            msg.putFailedCode("addRentInfo failed");
        return msg;
    }

    @RequestMapping(value = "/showdetail")
    public Map<String, Object> showDetail(@RequestBody Map o) {
        MsgMap msg = new MsgMap();
        int rid;
        try {
            rid = Integer.parseInt((String)o.get("rid"));
            RentInfo rentInfo = rentInfoService.getRentInfo(rid);
            if (rentInfo == null)
                msg.putFailedCode("can't get rent info");
            else {
                Limit limit  = Limit.getFromMap(o);
                if(limit.valid()){
                    List<Message> messages = messageService.getMessagesOfRentInfo(
                            rid, limit);

                    Customer customer = customerService.getCustomer(rentInfo.getCid());
                    if (customer == null) {
                        msg.putFailedCode("can't get customer of this rent");
                    } else {
                        msg.putSuccessCode();
                        msg.put("rentinfo", rentInfo);
                        msg.put("customer", customer);
                        msg.put("message", messages);
                    }
                }else{
                    msg.failForLimit();
                }

            }
        } catch (NullPointerException e) {
            msg.failForLackOfParam();
        }

        return msg;
    }

    @RequestMapping(value = "/rentinfos")
    public Map<String, Object> showRentInfos(@RequestBody Map o) {
        MsgMap msg = new MsgMap();
        try {
            Limit limit = Limit.getFromMap(o);
            if(limit.valid()){
                List<RentInfo> rentInfoList = rentInfoService.getRentInfos(limit);
                msg.putSuccessCode();
                msg.put("list", rentInfoList);
            }else{
                msg.failForLimit();
            }

        } catch (NullPointerException e) {
            msg.failForLackOfParam();
        }

        return msg;

    }

    @RequestMapping(value = "/count")
    public Map<String, Object> getCount() {
        MsgMap map = new MsgMap();
        map.putSuccessCode();
        map.put("count", rentInfoService.getCount());
        return map;
    }

    @RequestMapping(value = "/rentinfosbycid")
    public Map<String, Object> showRentInfosOfCustomer(@RequestBody Map o) {
        MsgMap msg = new MsgMap();
        try{
            int cid = Integer.parseInt((String)o.get("cid"));
            Limit limit = Limit.getFromMap(o);
            if(limit.valid()){
                List<RentInfo> rentInfos = rentInfoService.getRentInfosByCid(
                        cid,limit);

                msg.putSuccessCode();
                msg.put("list", rentInfos);
            }else
                msg.failForLimit();

        }catch (Exception e){
            msg.failForLackOfParam();
        }

        return msg;
    }
}
