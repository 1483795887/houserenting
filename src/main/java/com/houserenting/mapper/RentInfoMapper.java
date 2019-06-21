package com.houserenting.mapper;

import com.houserenting.entity.RentInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface RentInfoMapper {
    RentInfo sel(int rid);
    void add(RentInfo rentInfo);
}
