package com.houserenting.service;

import com.houserenting.entity.Customer;
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
public class CustomerServiceTest {

    @Autowired
    CustomerService service;

    private Customer customer;

    @Before
    @Transactional
    public void setUp() throws Exception {
        customer = new Customer();
        customer.setUsername("username1");
        customer.setPassword("123456");
        customer.setTel("18696104532");

        service.signup(customer);
    }

    @Test
    @Transactional
    public void nameAlreadyExist(){
        Customer customer1 = new Customer();
        customer1.setUsername("username1");
        assertFalse(service.signup(customer1));
    }

    @Test
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
        customer.setSex(Customer.FEMALE);
        service.updateInfo(customer);
        Customer customer1 = service.getCustomer(customer.getCid());
        assertEquals(customer1.getSex(), customer.getSex());
    }
}

