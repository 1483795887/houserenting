package com.houserenting.service.impl;

import com.houserenting.entity.Message;
import com.houserenting.mapper.MessageMapper;
import com.houserenting.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
