package com.houserenting.service;

import com.houserenting.entity.Costumer;

public interface CostumerService {
    boolean signup(Costumer costumer);
    Costumer login(String username, String password);
    boolean confirm(String username);
    boolean change(String username, String tel, String password, String newPassword);

    void updateInfo(Costumer costumer);

    Costumer getCostumer(int cid);
}
