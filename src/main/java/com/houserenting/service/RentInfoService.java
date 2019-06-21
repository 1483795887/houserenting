package com.houserenting.service;

import com.houserenting.entity.Customer;
import com.houserenting.entity.RentInfo;

public interface RentInfoService {
    boolean addRentInfo(RentInfo rentInfo);
    RentInfo getRentInfo(int rid);
    Customer getCustomerByCid(int cid);
}
