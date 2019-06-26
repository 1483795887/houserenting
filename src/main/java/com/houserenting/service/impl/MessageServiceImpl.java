package com.houserenting.service.impl;

import com.houserenting.entity.Message;
import com.houserenting.mapper.MessageMapper;
import com.houserenting.service.MessageService;
import com.houserenting.utils.Limit;
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
    public List<Message> getMessagesOfRentInfo(int rid, Limit limit) {
        Map<String, Object> map = new HashMap<>();
        map.put("rid", rid);
        map.put("begin", limit.getBegin());
        map.put("size", limit.getSize());
        List<Message> messages;
        try {
            messages = mapper.getMessagesOfRentInfo(map);
        } catch (Exception e) {
            messages = new ArrayList<>();
        }
        return messages;
    }

    @Override
    public void delete(int mid) {
        mapper.delete(mid);
    }
}
