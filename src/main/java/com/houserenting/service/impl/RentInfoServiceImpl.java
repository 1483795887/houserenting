package com.houserenting.service.impl;

import com.houserenting.entity.Customer;
import com.houserenting.entity.RentInfo;
import com.houserenting.mapper.CustomerMapper;
import com.houserenting.mapper.RentInfoMapper;
import com.houserenting.service.RentInfoService;
import com.houserenting.utils.Limit;
import org.omg.CORBA.OBJ_ADAPTER;
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

    private Map<String,Object> makeMap(Limit limit){
        Map<String, Object> map = new HashMap<>();
        map.put("begin", limit.getBegin());
        map.put("size", limit.getSize());
        return map;
    }

    private List<RentInfo> getRentInfos(Limit limit, int examined){
        List<RentInfo> infos ;
        try{
            Map<String,Object> map = makeMap(limit);
            map.put("examined",examined);
            infos = rentInfoMapper.getByPage(map);
        }catch (Exception e){
            infos = new ArrayList<>();
        }
        return infos;
    }

    @Override
    public List<RentInfo> getRentInfos(Limit limit) {
        return getRentInfos(limit, RentInfo.EXAMED);
    }

    @Override
    public int getCount() {
        return rentInfoMapper.getCount();
    }

    @Override
    public List<RentInfo> getRentInfosByCid( int cid, Limit limit) {
        List<RentInfo> infos;
        try{
            Map<String, Object> map = makeMap(limit);
            map.put("cid", cid);
            map.put("examined",RentInfo.EXAMED);
            infos = rentInfoMapper.getRentInfosByCid(map);
        }catch (Exception e){
            infos = new ArrayList<>();
        }

        return infos;
    }

    @Override
    public List<Map<String, Object>> getUnexaminedInfos(Limit limit) {
        List<Map<String,Object>> maps = new ArrayList<>();
        List<RentInfo> infos = getRentInfos(limit, RentInfo.UNEXAMED);
        for(RentInfo info:infos){
            Map<String,Object> map = new HashMap<>();
            map.put("rentinfo",info);
            Customer customer = customerMapper.sel(info.getCid());
            map.put("customer",customer);
            maps.add(map);
        }
        return maps;
    }

    @Override
    public void examine(int rid) {
        rentInfoMapper.examine(rid);
    }
}
