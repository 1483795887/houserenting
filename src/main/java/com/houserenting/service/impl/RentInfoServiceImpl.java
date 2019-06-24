package com.houserenting.service.impl;

import com.houserenting.entity.RentInfo;
import com.houserenting.mapper.CustomerMapper;
import com.houserenting.mapper.RentInfoMapper;
import com.houserenting.service.RentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RentInfoServiceImpl implements RentInfoService {
    private final RentInfoMapper rentInfoMapper;

    private final CustomerMapper customerMapper;

    @Autowired
    public RentInfoServiceImpl(RentInfoMapper rentInfoMapper, CustomerMapper customerMapper) {
        this.rentInfoMapper = rentInfoMapper;
        this.customerMapper = customerMapper;
    }

    //cid可能出错，比如不存在
    @Override
    public boolean addRentInfo(RentInfo rentInfo) {
        boolean result;
        try {
            rentInfoMapper.add(rentInfo);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public RentInfo getRentInfo(int rid) {
        return rentInfoMapper.sel(rid);
    }

    @Override
    public List<RentInfo> getRentInfos(int page, int size) {
        List<RentInfo> infos = new ArrayList<>();
        if (page <= 0 || size <= 0)
            return infos;
        Map<String, Object> map = new HashMap<>();
        map.put("begin", (page - 1) * size);
        map.put("size", size);
        infos = rentInfoMapper.getByPage(map);
        return infos;
    }

    @Override
    public int getCount() {
        return rentInfoMapper.getCount();
    }

    @Override
    public List<RentInfo> getRentInfosByCid(int page, int size, int cid) {
        List<RentInfo> infos = new ArrayList<>();
        if (page <= 0 || size <= 0)
            return infos;
        Map<String, Object> map = new HashMap<>();
        map.put("cid", cid);
        map.put("begin", (page - 1) * size);
        map.put("size", size);
        infos = rentInfoMapper.getByPage(map);
        return infos;
    }
}
