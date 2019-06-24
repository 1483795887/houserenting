package com.houserenting.mapper;

import com.houserenting.entity.Customer;
import com.houserenting.entity.RentInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentInfoMapperTest {
    @Autowired
    RentInfoMapper rentInfoMapper;

    @Autowired
    CustomerMapper customerMapper;

    private Customer customer;
    private RentInfo rentInfo;

    @Before
    public void setUp(){
        customer = new Customer();
        customer.setUsername("username");
        customer.setPassword("password");
        customer.setTel("18696104532");
        customer.setSex(Customer.MALE);
        customer.setRealname("realName");
        customer.setAddress("wuhan");
        customer.setAge(24);
        customerMapper.add(customer);
    }

    @Test
    @Transactional
    public void selRandomWhenNotDatabaseIsEmpty() {
        assertNull(rentInfoMapper.sel(100));
    }

    @Test
    @Transactional
    public void getRentInfoNullWhenNotExist(){
        assertNull(rentInfoMapper.sel(0));
    }

    @Test
    @Transactional
    public void whenAddedTheRidIsIncred(){
        RentInfo rentInfo = new RentInfo();
        rentInfo.setHuxing("三室两厅");
        rentInfo.setCid(customer.getCid());

        rentInfoMapper.add(rentInfo);

        RentInfo rentInfo1 = new RentInfo();
        rentInfo1.setHuxing("三室两厅");
        rentInfo1.setCid(customer.getCid());
        rentInfoMapper.add(rentInfo1);

        assertEquals(rentInfo.getRid() + 1, rentInfo1.getRid());
    }

    @Test
    @Transactional
    public void whenAddedTheHuxingIsTheSame(){
        RentInfo rentInfo = new RentInfo();
        rentInfo.setHuxing("三室两厅");
        rentInfo.setCid(customer.getCid());

        rentInfoMapper.add(rentInfo);

        RentInfo rentInfo1 = rentInfoMapper.sel(rentInfo.getRid());

        assertEquals(rentInfo1.getHuxing(), rentInfo.getHuxing());
    }

    @Test
    @Transactional
    public void whenAddedUsernameOfTheCustomerIsSame() {
        RentInfo rentInfo = new RentInfo();
        rentInfo.setCid(customer.getCid());

        rentInfoMapper.add(rentInfo);

        RentInfo rentInfo1 = rentInfoMapper.sel(rentInfo.getRid());

        Customer customer1 = customerMapper.sel(rentInfo1.getCid());

        assertEquals(customer1.getUsername(),customer.getUsername());
    }

    private void addTestData(int count){
        for(int i = 0 ;i < count ;i ++){
            RentInfo rentInfo = new RentInfo();
            rentInfo.setHuxing(String.format("%d",i));
            rentInfo.setCid(customer.getCid());

            rentInfoMapper.add(rentInfo);
        }
    }

    @Test
    @Transactional
    public void getRentInfosWhenCountIsNotEnough(){
        int count = 5;
        addTestData(count);

        Map<String,Object> map = new HashMap<>();
        map.put("begin",0);
        map.put("size", 10);

        List<RentInfo> rentInfos = rentInfoMapper.getByPage(map);

        assertEquals(rentInfos.size(), count);
    }

    @Test
    @Transactional
    public void getRentInfosWhenCountIsEnough(){
        int count = 20;

        addTestData(count);

        Map<String,Object> map = new HashMap<>();
        map.put("begin",10);
        map.put("size", 10);

        List<RentInfo> rentInfos = rentInfoMapper.getByPage(map);
        assertEquals(rentInfos.get(0).getHuxing(), "10");
    }

    @Test
    @Transactional
    public void testGetCountOfRentInfos(){
        int count = 10;
        addTestData(count);

        assertEquals(count, rentInfoMapper.getCount());
    }

    @Test
    @Transactional
    public void testGetRentInfosByCid(){
        int count = 100;
        addTestData(100);

        Map<String, Object> map = new HashMap<>();
        map.put("cid",customer.getCid());
        map.put("begin", 0);
        map.put("size", 10);

        assertEquals(10, rentInfoMapper.getRentInfosByCid(map).size());
    }

    @Test
    @Transactional
    public void testGetRentInfosByCid2(){
        int count = 100;
        addTestData(100);

        Map<String, Object> map = new HashMap<>();
        map.put("cid",customer.getCid());
        map.put("begin", 0);
        map.put("size", 10111);

        assertEquals(count, rentInfoMapper.getRentInfosByCid(map).size());
    }
}
