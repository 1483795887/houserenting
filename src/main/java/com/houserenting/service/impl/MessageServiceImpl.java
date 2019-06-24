package com.houserenting.service.impl;

import com.houserenting.entity.Message;
import com.houserenting.mapper.MessageMapper;
import com.houserenting.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageMapper mapper;

    @Autowired
    public MessageServiceImpl(MessageMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean add(Message message) {
        boolean result;
        try {
            mapper.add(message);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public Message sel(int mid) {
        return mapper.sel(mid);
    }

    @Override
    public List<Message> getMessagesOfRentInfo(int rid, int page, int size) {
        Map<String,Object> map = new HashMap<>();
        map.put("rid",rid);
        map.put("begin", (page - 1) * size);
        map.put("size", size);
        List<Message> messages;
        try{
            messages = mapper.getMessagesOfRentInfo(map);
        }catch (Exception e){
            messages = new ArrayList<>();
        }
        return messages;
    }
}
