package com.houserenting.mapper;

import com.houserenting.entity.Costumer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CostumerMapper {
    Costumer sel(int id);
    Costumer getCostumerByName(String username);
    int getCount();
    void add(Costumer costumer);
    void delete(int cid);
    void updatePassword(Costumer costumer);
    void updateInfo(Costumer costumer);
}
