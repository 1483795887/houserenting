package com.houserenting.mapper;

import com.houserenting.entity.Admin;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminMapperTest {

    private Admin admin;

    @Autowired
    AdminMapper mapper;

    @Before
    public void setUp() throws Exception {
        admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("123456");
        admin.setTel("123456789");
        mapper.add(admin);
    }

    @Test
    @Transactional
    public void testCount(){
        assertEquals(1, mapper.getCount());
    }

    @Test
    @Transactional
    public void afterAddCountIsAdded(){
        int count = mapper.getCount();
        Admin admin1 = new Admin();
        admin1.setUsername("aaa");
        admin1.setPassword("pa");
        admin1.setTel("123");
        mapper.add(admin1);
        assertEquals(count + 1, mapper.getCount());
    }

    @Test
    @Transactional
    public void addMakeAidInc(){

        Admin admin1 = new Admin();
        admin1.setUsername("admin1");
        admin1.setPassword("pa");
        admin1.setTel("tel");
        mapper.add(admin1);
        assertEquals(admin.getAid() + 1, admin1.getAid());
    }

    @Test(expected = DataIntegrityViolationException.class)
    @Transactional
    public void addSameUsername(){
        Admin admin1 = new Admin();
        admin1.setUsername("admin");
        admin1.setPassword("pass");
        admin1.setTel("tel");
        mapper.add(admin1);
    }

    private boolean equal(Admin admin1, Admin admin2){
        return admin1.getUsername().equals(admin2.getUsername())
                &&admin1.getPassword().equals(admin2.getPassword())
                &&admin1.getTel().equals(admin2.getTel());
    }

    @Test
    @Transactional
    public void addedDataRight(){
        assertTrue(equal(admin, mapper.sel(admin.getAid())));
    }

    @Test
    @Transactional
    public void afterDeleteTheCountIsRight(){
        int count = mapper.getCount();
        mapper.delete(admin.getAid());
        assertEquals(count - 1, mapper.getCount());
    }

    @Test
    @Transactional
    public void updateSuccess(){
        admin.setPassword("1234");
        mapper.updatePassword(admin);
        Admin customer1 = mapper.sel(admin.getAid());
        assertEquals(customer1.getPassword(),admin.getPassword());
    }
}
