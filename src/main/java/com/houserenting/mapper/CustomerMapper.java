package com.houserenting.mapper;

import com.houserenting.entity.Customer;
import org.springframework.stereotype.Repository;

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
