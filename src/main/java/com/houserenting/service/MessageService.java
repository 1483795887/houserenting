package com.houserenting.service;

import com.houserenting.entity.Message;

public interface MessageService {
    boolean add(Message message);

    Message sel(int mid);
}
