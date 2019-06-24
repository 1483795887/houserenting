package com.houserenting.service.impl;

import com.houserenting.entity.Customer;
import com.houserenting.mapper.CustomerMapper;
import com.houserenting.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper mapper;

    @Autowired
    public CustomerServiceImpl(CustomerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean signup(Customer customer) {
        boolean result;
        try {
            mapper.add(customer);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public Customer login(String username, String password) {
        Customer customer = mapper.getCustomerByName(username);
        if (customer == null)
            return null;
        if (!customer.getPassword().equals(password))
            return null;
        return customer;
    }

    @Override
    public boolean confirm(String username) {
        return mapper.getCustomerByName(username) != null;
    }

    @Override
    public boolean change(String username, String tel, String password, String newPassword) {
        Customer customer = mapper.getCustomerByName(username);
        if (customer == null)
            return false;
        if (!customer.getPassword().equals(password))
            return false;
        if (!customer.getTel().equals(tel))
            return false;
        customer.setPassword(newPassword);
        mapper.updatePassword(customer);
        return true;
    }

    @Override
    public void updateInfo(Customer customer) {
        mapper.updateInfo(customer);
    }

    @Override
    public Customer getCustomer(int cid) {
        return mapper.sel(cid);
    }

}
