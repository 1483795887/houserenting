package com.houserenting.mapper;

import com.houserenting.entity.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageMapper {
    int getCount();
    void add(Message message);
    Message sel(int mid);
}
