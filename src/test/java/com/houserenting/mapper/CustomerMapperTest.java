package com.houserenting.mapper;

import com.houserenting.entity.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerMapperTest {

    private Customer customer;

    final static int PAGE_NUM = 10;

    @Autowired
    CustomerMapper mapper;

    @Before
    public void setUp() throws Exception {
        customer = new Customer();
        customer.setUsername("username");
        customer.setPassword("password");
        customer.setAddress("address");
        customer.setAge(20);
        customer.setSex(0);
        customer.setRealname("realname");
        customer.setTel("tel");

        mapper.add(customer);
    }

    @Test
    @Transactional
    public void afterAddCountIsRight() {
        int count = mapper.getCount();
        Customer customer1 = new Customer();
        customer1.setUsername("customer1");
        customer1.setPassword("pa");
        customer1.setTel("tel");
        mapper.add(customer1);
        assertEquals("theCountIsNotRight", count + 1, mapper.getCount());
    }

    @Test(expected = DataIntegrityViolationException.class)
    @Transactional
    public void addSameUsername() {
        Customer customer1 = new Customer();
        customer1.setUsername("username");
        customer1.setPassword("pas");
        customer1.setTel("tel");
        mapper.add(customer1);
    }

    @Test
    @Transactional
    public void addMakeCidInc() {
        int cid = customer.getCid();
        Customer theCustomer = new Customer();
        theCustomer.setUsername("test");
        theCustomer.setPassword("pa");
        theCustomer.setTel("tel");
        mapper.add(theCustomer);
        assertEquals(cid + 1, theCustomer.getCid());
    }

    private boolean equal(Customer customer1, Customer customer2) {
        return customer1.getUsername().equals(customer2.getUsername())
                && customer1.getPassword().equals(customer2.getPassword())
                && customer1.getAddress().equals(customer2.getAddress())
                && customer1.getAge() == customer2.getAge()
                && customer1.getRealname().equals(customer2.getRealname())
                && customer1.getSex() == customer2.getSex()
                && customer1.getTel().equals(customer2.getTel());
    }

    @Test
    @Transactional
    public void addedDataIsRight() {
        assertTrue(equal(customer, mapper.sel(customer.getCid())));
    }

    @Test
    @Transactional
    public void afterDeleteTheCountIfRight() {
        int count = mapper.getCount();
        mapper.delete(customer.getCid());
        assertEquals("deleteFailed", count - 1, mapper.getCount());
    }

    @Test
    @Transactional
    public void updatePasswordSuccess() {
        customer.setPassword("1234");
        mapper.updatePassword(customer);
        Customer customer1 = mapper.sel(customer.getCid());
        assertEquals(customer1.getPassword(), customer.getPassword());
    }

    @Test
    @Transactional
    public void updateSexSuccess() {
        customer.setSex(Customer.FEMALE);
        mapper.updateInfo(customer);
        Customer customer1 = mapper.sel(customer.getCid());
        assertEquals(customer1.getSex(), customer.getSex());
    }
}
