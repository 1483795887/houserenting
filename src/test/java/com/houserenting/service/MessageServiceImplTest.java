package com.houserenting.service;

import com.houserenting.entity.Customer;
import com.houserenting.entity.Message;
import com.houserenting.entity.RentInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceImplTest {

    @Autowired
    MessageService service;

    @Autowired
    CustomerService customerService;

    @Autowired
    RentInfoService rentInfoService;

    private Customer customer;
    private RentInfo rentInfo;

    @Before
    public void setUp() {
        customer = new Customer();
        customer.setUsername("user");
        customerService.signup(customer);

        rentInfo = new RentInfo();
        rentInfo.setHuxing("huxing");
        rentInfo.setCid(customer.getCid());
        rentInfoService.addRentInfo(rentInfo);
    }

    @Test
    @Transactional
    public void addFailWhenCustomerNotExist() {
        Message message = new Message();
        message.setRid(rentInfo.getRid());

        assertFalse( service.add(message));
    }

    @Test
    @Transactional
    public void addFailWhenRentInfoNotExist() {
        Message message = new Message();
        message.setCustomer(customer);

        assertFalse( service.add(message));
    }

    @Test
    @Transactional
    public void addSuccess() {
        Message message = new Message();
        message.setCustomer(customer);
        message.setRid(rentInfo.getRid());

        message.setContent("sdsfadfas");

        assertTrue(service.add(message));
    }

    @Test
    @Transactional
    public void ContentRightAfterAdd() {
        Message message = new Message();
        message.setCustomer(customer);
        message.setRid(rentInfo.getRid());

        message.setContent("sdsfadfas");

        service.add(message);

        Message message1 = service.sel(message.getMid());

        assertEquals(message.getContent(), message1.getContent());
    }

}