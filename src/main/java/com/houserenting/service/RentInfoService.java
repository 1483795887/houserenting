package com.houserenting.service;

import com.houserenting.entity.RentInfo;
import com.houserenting.utils.Limit;

import java.util.List;
import java.util.Map;

public interface RentInfoService {
    boolean addRentInfo(RentInfo rentInfo);

    RentInfo getRentInfo(int rid);

    List<RentInfo> getRentInfos(Limit limit);

    int getCount();

    List<RentInfo> getRentInfosByCid(int cid, Limit limit);

    List<Map<String, Object>> getUnexaminedInfos(Limit limit);

    void examine(int rid);
}
