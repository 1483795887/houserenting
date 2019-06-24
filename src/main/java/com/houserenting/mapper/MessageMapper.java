package com.houserenting.mapper;

import com.houserenting.entity.Message;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MessageMapper {
    int getCount();

    void add(Message message);

    Message sel(int mid);

    List<Message> getMessagesOfRentInfo(Map<String, Object> map);
}
