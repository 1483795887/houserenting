package com.houserenting.controller;

import com.houserenting.entity.Customer;
import com.houserenting.entity.RentInfo;
import com.houserenting.service.CustomerService;
import com.houserenting.service.RentInfoService;
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

    @Autowired
    public RentInfoController(RentInfoService rentInfoService, CustomerService customerService) {
        this.rentInfoService = rentInfoService;
        this.customerService = customerService;
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
        RentInfo rentInfo = rentInfoService.getRentInfo((int) o.get("rid"));
        if (rentInfo == null)
            msg.putFailedCode("can't get rent info");
        else {
            Customer customer = customerService.getCustomer(rentInfo.getCid());
            if (customer == null) {
                msg.putFailedCode("can't get customer of this rent");
            } else {
                msg.putSuccessCode();
                msg.put("rentinfo", rentInfo);
                msg.put("customer", customer);
            }
        }

        return msg;
    }


    @RequestMapping(value = "/rentinfos")
    public Map<String, Object> showRentInfos(@RequestBody Map o) {
        int page = (int) o.get("page");
        int size = (int) o.get("size");
        List<RentInfo> rentInfoList = rentInfoService.getRentInfos(page, size);

        MsgMap msg = new MsgMap();
        msg.putSuccessCode();
        msg.put("list", rentInfoList);

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
        int page = (int) o.get("page");
        int size = (int) o.get("size");
        int cid = (int) o.get("cid");

        List<RentInfo> rentInfos = rentInfoService.getRentInfosByCid(page, size, cid);

        MsgMap msg = new MsgMap();
        msg.putSuccessCode();
        msg.put("list", rentInfos);

        return msg;
    }
}
