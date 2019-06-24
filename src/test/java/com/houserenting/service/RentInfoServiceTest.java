package com.houserenting.service;

import com.houserenting.entity.Customer;
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
public class RentInfoServiceTest {
    @Autowired
    RentInfoService service;

    @Autowired
    CustomerService customerService;

    private Customer customer;

    @Before
    public void setUp() {
        customer = new Customer();
        customer.setUsername("username1");
        customer.setPassword("123456");
        customer.setTel("18696104532");
        customerService.signup(customer);
    }

    @Test
    @Transactional
    public void selEmpty() {
        assertNull(service.getRentInfo(1000));
    }

    @Test
    @Transactional
    public void whenCustomerIdNotExist() {
        assertFalse(service.addRentInfo(new RentInfo()));
    }

    @Test
    @Transactional
    public void afterAddedTheRentInfoIsRight() {

        RentInfo rentInfo = new RentInfo();
        rentInfo.setHuxing("aaaa");

        rentInfo.setCid(customer.getCid());

        service.addRentInfo(rentInfo);

        RentInfo rentInfo1 = service.getRentInfo(rentInfo.getRid());

        assertEquals(rentInfo1.getHuxing(), rentInfo.getHuxing());

    }

    @Test
    @Transactional
    public void afterAddedTheCustomerIsRight() {

        RentInfo rentInfo = new RentInfo();
        rentInfo.setHuxing("aaaa");

        rentInfo.setCid(customer.getCid());

        service.addRentInfo(rentInfo);

        RentInfo rentInfo1 = service.getRentInfo(rentInfo.getRid());
        Customer customer1 = customerService.getCustomer(rentInfo1.getCid());

        assertEquals(customer1.getUsername(), customer.getUsername());

    }

    private void addTestData(int count) {
        for (int i = 0; i < count; i++) {
            RentInfo rentInfo = new RentInfo();
            rentInfo.setHuxing(String.format("%d", i));
            rentInfo.setCid(customer.getCid());

            service.addRentInfo(rentInfo);
        }
    }

    @Test
    @Transactional
    public void getListWhenPageIsMinus() {
        int count = 10;
        addTestData(count);

        assertEquals(0, service.getRentInfos(-1, 10).size());
    }

    @Test
    @Transactional
    public void getListEmptyWhenSizeIsMinus() {
        int count = 10;
        addTestData(count);
        assertEquals(0, service.getRentInfos(1, -1).size());
    }

    @Test
    @Transactional
    public void getListSizeRight() {
        int existCount = service.getCount();

        int count = 10;
        addTestData(count);

        int size = 6;
        int page = 2 + existCount / size;
        int newCount = 4 + existCount % size;

        assertEquals(newCount, service.getRentInfos(page, size).size());
    }

    @Test
    @Transactional
    public void testGetCount() {
        int count = service.getCount();
        addTestData(100);

        assertEquals(count + 100, service.getCount());
    }
}