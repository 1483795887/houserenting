package com.houserenting.controller;

import com.houserenting.entity.Customer;
import com.houserenting.entity.Message;
import com.houserenting.service.MessageService;
import com.houserenting.utils.MsgMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/message", method = RequestMethod.POST)
public class MessageController {
    private final MessageService service;

    @Autowired
    public MessageController(MessageService service){
        this.service = service;
    }

    @RequestMapping("/add")
    public Map<String,Object> addMessage(@RequestBody Map o){
        MsgMap msg = new MsgMap();
        try{
            String title = (String)o.get("title");
            String content = (String)o.get("content");
            String time = (String)o.get("time");
            int cid = (int)o.get("cid");
            int rid = (int)o.get("rid");
            Customer customer = new Customer();
            customer.setCid(cid);

            Message message = new Message();
            message.setCustomer(customer);
            message.setRid(rid);
            message.setTitle(title);
            message.setTime(time);
            message.setContent(content);

            boolean result = service.add(message);
            if(result)
                msg.putSuccessCode();
            else
                msg.putFailedCode("add message failed");
        }catch (NullPointerException e){
            msg.failForLackOfParam();
        }
        return msg;
    }
}
