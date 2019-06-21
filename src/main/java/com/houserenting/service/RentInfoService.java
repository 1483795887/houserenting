package com.houserenting.service;

import com.houserenting.entity.Customer;
import com.houserenting.entity.RentInfo;

import java.util.List;

public interface RentInfoService {
    boolean addRentInfo(RentInfo rentInfo);
    RentInfo getRentInfo(int rid);
    List<RentInfo> getRentInfos(int page, int size);
    int getCount();
}
