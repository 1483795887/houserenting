package com.houserenting.mapper;

import com.houserenting.entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {
    int getCount();

    void add(Admin admin);

    Admin sel(int aid);

    Admin getAdminByName(String username);

    void delete(int aid);

    void updatePassword(Admin admin);
}
