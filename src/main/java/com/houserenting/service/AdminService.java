package com.houserenting.service;

import com.houserenting.entity.Admin;

public interface AdminService {
    boolean signup(Admin admin);

    Admin login(String username, String password);

    boolean confirm(String username);

    boolean change(String username, String tel, String password, String newPassword);

}
