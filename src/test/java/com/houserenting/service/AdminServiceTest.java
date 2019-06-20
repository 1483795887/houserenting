package com.houserenting.service;

import com.houserenting.entity.Admin;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {

    @Autowired
    AdminService service;

    Admin admin;

    @Before
    @Transactional
    public void setUp() throws Exception {

        admin = new Admin();
        admin.setUsername("username1");
        admin.setPassword("123456");
        admin.setTel("18696104532");
        service.signup(admin);
    }

    @Test
    @Transactional
    public void signupForSameUsernameFailed(){
        Admin admin1 = new Admin();
        admin1.setUsername("username1");
        admin1.setPassword("123");
        admin1.setTel("1234");
        assertFalse(service.signup(admin1));
    }

    @Test
    @Transactional
    public void signupSuccess(){
        Admin admin1 = new Admin();
        admin1.setUsername("username");
        admin1.setPassword("password");
        admin1.setTel("123456789");
        assertTrue(service.signup(admin1));
    }

    @Test
    @Transactional
    public void loginPasswordNullFail(){
        assertNull(service.login("username1",null));
    }

    @Test
    @Transactional
    public void loginUsernameNotExistFail(){
        assertNull(service.login("usr","123456"));
    }

    @Test
    @Transactional
    public void loginUsernameNotMatchPassword(){
        assertNull(service.login("username1","123"));
    }

    @Test
    @Transactional
    public void loginSuccess(){
        assertNotNull(service.login("username1","123456"));
    }

    @Transactional
    public void usernameNotExist(){
        assertFalse(service.confirm("username"));
    }

    @Test
    @Transactional
    public void usernameExist(){
        assertTrue(service.confirm("username1"));
    }

    @Test
    @Transactional
    public void userNotExistChangeFail(){
        assertFalse(service.change("username", "tel","pass" ,
                "pass"));
    }

    @Test
    @Transactional
    public void userExistButPassNotRightFail(){
        assertFalse(service.change("username1","18696104532",
                "111","111"));
    }

    @Test
    @Transactional
    public void userExistButTelNotRightFail(){
        assertFalse(service.change("username1","1234",
                "123456","1234"));
    }

    @Test
    @Transactional
    public void changeSuccess(){
        service.change("username1", "18696104532",
                "123456","1234");

        assertNotNull(service.login("username1","1234"));
    }
}
