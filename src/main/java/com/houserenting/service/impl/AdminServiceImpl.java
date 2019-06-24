package com.houserenting.service.impl;

import com.houserenting.entity.Admin;
import com.houserenting.mapper.AdminMapper;
import com.houserenting.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminMapper mapper;

    @Autowired
    public AdminServiceImpl(AdminMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean signup(Admin admin) {
        boolean result;
        try {
            mapper.add(admin);
            result = true;
        } catch (Exception e) {
            result = false;
        }

        return result;
    }

    @Override
    public Admin login(String username, String password) {
        Admin admin = mapper.getAdminByName(username);
        if (admin == null)
            return null;
        if (!admin.getPassword().equals(password))
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
        if (admin == null)
            return false;
        if (!admin.getPassword().equals(password))
            return false;
        if (!admin.getTel().equals(tel))
            return false;
        admin.setPassword(newPassword);
        mapper.updatePassword(admin);
        return true;
    }
}
