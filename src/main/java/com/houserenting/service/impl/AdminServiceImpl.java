package com.houserenting.service.impl;

import com.houserenting.entity.Admin;
import com.houserenting.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.houserenting.mapper.AdminMapper;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper mapper;

    @Override
    public boolean signup(Admin admin) {
        boolean result = true;
        try{
            mapper.add(admin);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    @Override
    public Admin login(String username, String password) {
        Admin admin = mapper.getAdminByName(username);
        if(admin == null)
            return null;
        if(!admin.getPassword().equals(password))
            return null;
        return admin;
    }

    @Override
    public boolean confirm(String username) {
        return mapper.getAdminByName(username) != null;
    }

    @Override
    public boolean change(String username, String tel, String password, String newPassword) {
        Admin admin = mapper.getAdminByName(username);
        if(admin == null)
            return false;
        if(!admin.getPassword().equals(password))
            return false;
        if(!admin.getTel().equals(tel))
            return false;
        admin.setPassword(newPassword);
        mapper.updatePassword(admin);
        return true;
    }
}
