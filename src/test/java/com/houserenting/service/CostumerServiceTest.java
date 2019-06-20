package com.houserenting.service;

import com.houserenting.entity.Costumer;
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
public class CostumerServiceTest {

    @Autowired
    CostumerService service;

    Costumer costumer;

    @Before
    @Transactional
    public void setUp() throws Exception {
        costumer = new Costumer();
        costumer.setUsername("username1");
        costumer.setPassword("123456");
        costumer.setTel("18696104532");

        service.signup(costumer);
    }

    @Transactional
    public void usernameNotExistFail(){
        assertNull(service.login("usr","123456"));
    }

    @Test
    @Transactional
    public void usernameNotMatchPassword(){
        assertNull(service.login("username1","123"));
    }

    @Test
    @Transactional
    public void loginSuccess(){
        assertNotNull(service.login("username1","123456"));
    }

    @Test
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

    @Test
    @Transactional
    public void changeSex(){
        costumer.setSex(Costumer.FEMALE);
        service.updateInfo(costumer);
        Costumer costumer1 = service.getCostumer(costumer.getCid());
        assertEquals(costumer1.getSex(), costumer.getSex());
    }
}

