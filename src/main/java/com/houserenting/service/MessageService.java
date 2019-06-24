package com.houserenting.service;

import com.houserenting.entity.Message;
import com.houserenting.utils.Limit;

import java.util.List;

public interface MessageService {
    boolean add(Message message);

    Message sel(int mid);

    List<Message> getMessagesOfRentInfo(int rid, Limit limit);
}
