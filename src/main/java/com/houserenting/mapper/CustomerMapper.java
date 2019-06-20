package com.houserenting.mapper;

import com.houserenting.entity.Customer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CustomerMapper {
    Customer sel(int id);
    Customer getCustomerByName(String username);
    int getCount();
    void add(Customer customer);
    void delete(int cid);
    void updatePassword(Customer customer);
    void updateInfo(Customer customer);
}
