package com.houserenting.service;

import com.houserenting.entity.Message;

import java.util.List;

public interface MessageService {
    boolean add(Message message);

    Message sel(int mid);

    List<Message> getMessagesOfRentInfo(int rid, int page, int size);
}
