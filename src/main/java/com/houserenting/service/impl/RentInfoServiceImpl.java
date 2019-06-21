package com.houserenting.service.impl;

import com.houserenting.entity.Customer;
import com.houserenting.entity.RentInfo;
import com.houserenting.mapper.CustomerMapper;
import com.houserenting.mapper.RentInfoMapper;
import com.houserenting.service.RentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class RentInfoServiceImpl implements RentInfoService {
    @Autowired
    RentInfoMapper rentInfoMapper;

    @Autowired
    CustomerMapper customerMapper;

    //cid可能出错，比如不存在
    @Override
    public boolean addRentInfo(RentInfo rentInfo) {
        boolean result;
        try{
            rentInfoMapper.add(rentInfo);
            result = true;
        }catch (DataIntegrityViolationException e){
            result = false;
            e.printStackTrace();;
        }
        return result;
    }

    @Override
    public RentInfo getRentInfo(int rid) {
        return rentInfoMapper.sel(rid);
    }

    @Override
    public Customer getCustomerByCid(int cid) {
        return customerMapper.sel(cid);
    }
}
