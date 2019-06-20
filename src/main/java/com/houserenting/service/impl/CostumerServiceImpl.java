package com.houserenting.service.impl;

import com.houserenting.entity.Costumer;
import com.houserenting.mapper.CostumerMapper;
import com.houserenting.service.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CostumerServiceImpl implements CostumerService {
    @Autowired
    CostumerMapper mapper;

    boolean stringLegal(String str) {
        if (str == null)
            return false;
        if (str.equals(""))
            return false;
        return true;
    }

    @Override
    public boolean signup(Costumer costumer) {
        boolean result;
        try {
            mapper.add(costumer);
            result = true;
        } catch (DataIntegrityViolationException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Costumer login(String username, String password) {
        Costumer costumer = mapper.getCostumerByName(username);
        if (costumer == null)
            return null;
        if (!costumer.getPassword().equals(password))
            return null;
        return costumer;
    }

    @Override
    public boolean confirm(String username) {
        return mapper.getCostumerByName(username) != null;
    }

    @Override
    public boolean change(String username, String tel, String password, String newPassword) {
        Costumer costumer = mapper.getCostumerByName(username);
        if (costumer == null)
            return false;
        if (!costumer.getPassword().equals(password))
            return false;
        if (!costumer.getTel().equals(tel))
            return false;
        costumer.setPassword(newPassword);
        mapper.updatePassword(costumer);
        return true;
    }

    @Override
    public void updateInfo(Costumer costumer) {
        mapper.updateInfo(costumer);
    }

    @Override
    public Costumer getCostumer(int cid) {
        return mapper.sel(cid);
    }

}
