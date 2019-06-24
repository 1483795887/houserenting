package com.houserenting.mapper;

import com.houserenting.entity.Customer;
import com.houserenting.entity.Message;
import com.houserenting.entity.RentInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageMapperTest {

    private final static int testCustomerNum = 10;
    private final static int testRentInfoNum = 10;
    @Autowired
    MessageMapper mapper;
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    RentInfoMapper rentInfoMapper;
    private Customer customer;
    private RentInfo rentInfo;
    private Message message;
    private List<Customer> testCustomers;
    private List<RentInfo> testRentInfos;

    @Before
    @Transactional
    public void setUp() {
        customer = new Customer();
        customer.setUsername("username");

        customerMapper.add(customer);

        rentInfo = new RentInfo();
        rentInfo.setHuxing("testhuxing");
        rentInfo.setCid(customer.getCid());

        rentInfoMapper.add(rentInfo);

        message = new Message();
    }

    @Test
    @Transactional
    public void countIncWhenAdded() {
        int count = mapper.getCount();

        message.setCustomer(customer);
        message.setRid(rentInfo.getRid());

        mapper.add(message);

        assertEquals(count + 1, mapper.getCount());
    }

    @Test(expected = DataIntegrityViolationException.class)
    @Transactional
    public void addWhenCustomerNotExist() {
        message.setRid(rentInfo.getRid());

        mapper.add(message);
    }

    @Test(expected = DataIntegrityViolationException.class)
    @Transactional
    public void addWhenRentInfoNotExist() {
        message.setCustomer(customer);

        mapper.add(message);
    }

    @Test
    @Transactional
    public void midIncWhenAdded() {
        message.setCustomer(customer);
        message.setRid(rentInfo.getRid());

        mapper.add(message);

        Message message1 = new Message();
        message1.setCustomer(customer);
        message1.setRid(rentInfo.getRid());

        mapper.add(message1);

        assertEquals(message.getMid() + 1, message1.getMid());
    }

    @Test
    @Transactional
    public void contentRightWhenSel() {
        message.setCustomer(customer);
        message.setRid(rentInfo.getRid());

        message.setContent("sadfa");

        mapper.add(message);

        Message message1 = mapper.sel(message.getMid());
        assertEquals(message1.getContent(), message.getContent());
    }

    @Test
    @Transactional
    public void customertRightWhenSel() {
        message.setCustomer(customer);
        message.setRid(rentInfo.getRid());

        message.setContent("sadfa");

        mapper.add(message);

        Message message1 = mapper.sel(message.getMid());
        assertEquals(message1.getCustomer().getUsername(), message.getCustomer().getUsername());
    }

    private void addTestData() {
        testCustomers = new ArrayList<>();
        testRentInfos = new ArrayList<>();

        for (int c = 0; c < testCustomerNum; c++) {
            Customer customer = new Customer();
            customer.setUsername("user" + c);
            testCustomers.add(customer);
            customerMapper.add(customer);
        }

        for (int r = 0; r < testRentInfoNum; r++) {
            RentInfo rentInfo = new RentInfo();
            rentInfo.setHuxing("huxing" + r);
            rentInfo.setCid(testCustomers.get(r).getCid());
            testRentInfos.add(rentInfo);
            rentInfoMapper.add(rentInfo);
            for (int c = 0; c < testCustomerNum; c++) {
                Message message = new Message();
                message.setRid(rentInfo.getRid());
                message.setCustomer(testCustomers.get(c));

                message.setContent(rentInfo.getHuxing()
                        + testCustomers.get(c).getUsername()
                        + " content");

                mapper.add(message);
            }
        }

    }

    private Map<String, Object> makeParaMap(int rid, int begin, int size) {
        Map<String, Object> map = new HashMap<>();
        map.put("rid", rid);
        map.put("begin", begin);
        map.put("size", size);

        return map;
    }

    @Test
    @Transactional
    public void countRightWhenGetMessagesByRentInfoFull() {
        addTestData();

        assertEquals(testRentInfoNum,
                mapper.getMessagesOfRentInfo(
                        makeParaMap(testRentInfos.get(2).getRid(),
                                0,
                                testCustomerNum * testRentInfoNum)).size());
    }

    @Test
    @Transactional
    public void countRightWhenGetMessageRentInfoNotFull() {
        addTestData();

        assertEquals(5,
                mapper.getMessagesOfRentInfo(
                        makeParaMap(testRentInfos.get(2).getRid(),
                                0,
                                5)).size());
    }

    @Test
    @Transactional
    public void countRightWhenGetMessageRentInfoBeginIsTooHigh() {
        addTestData();

        assertEquals(0,
                mapper.getMessagesOfRentInfo(
                        makeParaMap(testRentInfos.get(2).getRid(),
                                testCustomerNum * testCustomerNum,
                                testCustomerNum)).size());
    }

    @Test
    @Transactional
    public void contentRightWhenGetMessageRentInfoBegin() {
        addTestData();

        assertEquals("huxing2user3 content",
                mapper.getMessagesOfRentInfo(
                        makeParaMap(testRentInfos.get(2).getRid(),
                                0,
                                testCustomerNum)).get(3).getContent());
    }
}