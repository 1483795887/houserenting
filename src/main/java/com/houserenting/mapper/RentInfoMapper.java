package com.houserenting.mapper;

import com.houserenting.entity.RentInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RentInfoMapper {
    RentInfo sel(int rid);

    void add(RentInfo rentInfo);

    List<RentInfo> getByPage(Map<String, Object> map);

    int getCount();

    List<RentInfo> getRentInfosByCid(Map<String, Object> map);

    void examine(int rid);
}
