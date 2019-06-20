package com.houserenting.service;

import com.houserenting.entity.Customer;

public interface CustomerService {
    boolean signup(Customer customer);
    Customer login(String username, String password);
    boolean confirm(String username);
    boolean change(String username, String tel, String password, String newPassword);

    void updateInfo(Customer customer);

    Customer getCustomer(int cid);
}
