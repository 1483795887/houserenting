package com.houserenting.mapper;

import com.houserenting.entity.Customer;
import com.houserenting.entity.RentInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
    public void setUp() {
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
    public void getRentInfoNullWhenNotExist() {
        assertNull(rentInfoMapper.sel(0));
    }

    @Test
    @Transactional
    public void whenAddedTheRidIsIncred() {
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
    public void whenAddedTheHuxingIsTheSame() {
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

        assertEquals(customer1.getUsername(), customer.getUsername());
    }

    private void addTestDataExamined(int count) {
        addTestData(count, RentInfo.EXAMED);
    }

    private void addTestDataUnExamined(int count){
        addTestData(count, RentInfo.UNEXAMED);
    }

    private void addTestData(int count, int examined){
        for(int i = 0; i < count; i ++){
            RentInfo rentInfo = new RentInfo();
            rentInfo.setHuxing(String.format("%d", i));
            rentInfo.setCid(customer.getCid());
            rentInfo.setExamined(examined);

            rentInfoMapper.add(rentInfo);
        }
    }

    private Map<String, Object> makeLimit(int begin, int size) {
        Map<String, Object> map = new HashMap<>();
        map.put("begin", begin);
        map.put("size", size);
        return map;
    }

    private int getExaminedCount(){
        int count = rentInfoMapper.getCount();
        List<RentInfo> rentInfos = rentInfoMapper.getExaminedByPage(makeLimit(0,count));
        return rentInfos.size();
    }

    @Test
    @Transactional
    public void getRentInfosWhenCountIsNotEnough() {
        int existCount = rentInfoMapper.getCount();
        int count = 5;
        int examinedCount = getExaminedCount();
        addTestDataExamined(count);

        int size = count + existCount + 5;

        List<RentInfo> rentInfos = rentInfoMapper.getExaminedByPage(
                makeLimit(0,size));

        assertEquals(rentInfos.size(), count + examinedCount);
    }

    @Test
    @Transactional
    public void getRentInfosWhenCountIsEnough() {
        int count = 20;
        int existCount = rentInfoMapper.getCount();

        int examinedCount = getExaminedCount();

        addTestDataExamined(count);

        int begin = 10 + examinedCount;
        int size = 10;

        List<RentInfo> rentInfos = rentInfoMapper.getExaminedByPage(makeLimit(begin, size));
        assertEquals(rentInfos.get(0).getHuxing(), "10");
    }

    @Test
    @Transactional
    public void testGetCountOfRentInfos() {
        int count = rentInfoMapper.getCount();

        addTestDataExamined(10);

        assertEquals(count + 10, rentInfoMapper.getCount());
    }

    @Test
    @Transactional
    public void testGetRentInfosByCid() {
        int count = 100;
        addTestDataExamined(100);

        Map<String, Object> map = makeLimit(0,10);
        map.put("cid", customer.getCid());

        assertEquals(10, rentInfoMapper.getRentInfosByCid(map).size());
    }

    @Test
    @Transactional
    public void testGetRentInfosByCid2() {
        int count = 100;
        addTestDataExamined(100);

        Map<String, Object> map = makeLimit(0, 10111);
        map.put("cid", customer.getCid());

        assertEquals(count, rentInfoMapper.getRentInfosByCid(map).size());
    }

    @Test
    @Transactional
    public void noUmExaminedInfoWhenGetRentInfosThenCountZero() {
        int count = rentInfoMapper.getCount();
        List<RentInfo> exist = rentInfoMapper.getExaminedByPage(makeLimit(0,count));

        int examinedCount = exist.size();

        addTestDataUnExamined(100);

        exist = rentInfoMapper.getExaminedByPage(
                makeLimit(0, count + 100));

        assertEquals(examinedCount,exist.size());

    }

    @Test
    @Transactional
    public void bothExaminedAndUnexaminedWhenGetRentInfosThenCountRight() {
        int count = rentInfoMapper.getCount();
        List<RentInfo> exist = rentInfoMapper.getExaminedByPage(makeLimit(0,count));

        int examinedCount = exist.size();

        addTestDataUnExamined(100);
        addTestDataExamined(100);

        exist = rentInfoMapper.getExaminedByPage(
                makeLimit(0, count + 200));

        assertEquals(examinedCount + 100,exist.size());

    }
}
