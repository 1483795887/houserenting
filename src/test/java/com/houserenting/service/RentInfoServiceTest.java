package com.houserenting.service;

import com.houserenting.entity.Customer;
import com.houserenting.entity.RentInfo;
import com.houserenting.utils.Limit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentInfoServiceTest {
    @Autowired
    RentInfoService service;

    @Autowired
    CustomerService customerService;

    private Customer customer;

    private Limit limit;

    @Before
    public void setUp() {
        customer = new Customer();
        customer.setUsername("username1");
        customer.setPassword("123456");
        customer.setTel("18696104532");
        customerService.signup(customer);
        limit = new Limit();
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
            rentInfo.setExamined(RentInfo.EXAMED);

            service.addRentInfo(rentInfo);
        }
    }

    private void setLimit(int page, int size) {
        limit.setPage(page);
        limit.setSize(size);
    }

    @Test
    @Transactional
    public void getListWhenPageIsMinus() {
        int count = 10;
        addTestData(count);

        setLimit(-1, 10);

        assertEquals(0, service.getRentInfos(limit).size());
    }

    @Test
    @Transactional
    public void getListEmptyWhenSizeIsMinus() {
        int count = 10;
        addTestData(count);

        setLimit(1, -1);

        assertEquals(0, service.getRentInfos(limit).size());
    }

    @Test
    @Transactional
    public void getListSizeRight() {
        int existCount = service.getCount();

        setLimit(1, existCount);
        List<RentInfo> infos = service.getRentInfos(limit);
        int examinedCount = infos.size();

        int count = 10;
        addTestData(count);

        int size = 6;
        int page = 2 + examinedCount / size;
        int newCount = 4 + examinedCount % size;

        setLimit(page, size);

        assertEquals(newCount, service.getRentInfos(limit).size());
    }

    @Test
    @Transactional
    public void testGetCount() {
        int count = service.getCount();
        addTestData(100);

        assertEquals(count + 100, service.getCount());
    }

    @Test
    @Transactional
    public void whenGettingRentInfosByCidThenCountRight(){
        addTestData(100);

        setLimit(1, 10);

        assertEquals(10, service.getRentInfosByCid(customer.getCid(), limit).size());
    }

    @Test
    @Transactional
    public void whenPageMinusOfGettingRentInfosByCidThenEmpty(){
        addTestData(100);

        setLimit(-1, 10);

        assertEquals(0, service.getRentInfosByCid(customer.getCid(), limit).size());
    }

    @Test
    @Transactional
    public void whenSizeMinueGettingRentInfosByCidThenEmpty(){
        addTestData(100);

        setLimit(1, -10);

        assertEquals(0, service.getRentInfosByCid(customer.getCid(), limit).size());
    }
}