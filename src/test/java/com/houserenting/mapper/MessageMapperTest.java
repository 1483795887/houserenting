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

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageMapperTest {

    @Autowired
    MessageMapper mapper;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    RentInfoMapper rentInfoMapper;


    private Customer customer;
    private RentInfo rentInfo;
    private Message message;

    @Before
    @Transactional
    public void setUp(){
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
    public void getCountZeroWhenEmpty() {
        assertEquals(0, mapper.getCount());
    }

    @Test
    @Transactional
    public void countIncWhenAdded(){
        int count = mapper.getCount();

        message.setCid(customer.getCid());
        message.setRid(rentInfo.getRid());

        mapper.add(message);

        assertEquals(count + 1, mapper.getCount());
    }

    @Test(expected = DataIntegrityViolationException.class)
    @Transactional
    public void addWhenCustomerNotExist(){
        message.setRid(rentInfo.getRid());

        mapper.add(message);
    }

    @Test(expected = DataIntegrityViolationException.class)
    @Transactional
    public void addWhenRentInfoNotExist(){
        message.setCid(customer.getCid());

        mapper.add(message);
    }

    @Test
    @Transactional
    public void midIncWhenAdded(){
        message.setCid(customer.getCid());
        message.setRid(rentInfo.getRid());

        mapper.add(message);

        Message message1 = new Message();
        message1.setCid(customer.getCid());
        message1.setRid(rentInfo.getRid());

        mapper.add(message1);

        assertEquals(message.getMid()+ 1, message1.getMid() );
    }

    @Test
    @Transactional
    public void contentRightWhenSel(){
        message.setCid(customer.getCid());
        message.setRid(rentInfo.getRid());

        message.setContent("sadfa");

        mapper.add(message);

        Message message1 = mapper.sel(message.getMid());
        assertEquals(message1.getContent(), message.getContent());
    }
}