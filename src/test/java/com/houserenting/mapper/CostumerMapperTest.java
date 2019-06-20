package com.houserenting.mapper;

import com.houserenting.entity.Costumer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CostumerMapperTest {

    Costumer costumer;

    final static int PAGE_NUM = 10;

    @Autowired
    CostumerMapper mapper;

    @Before
    public void setUp() throws Exception {
        costumer = new Costumer();
        costumer.setUsername("username");
        costumer.setPassword("password");
        costumer.setAddress("address");
        costumer.setAge(20);
        costumer.setSex(0);
        costumer.setRealname("realname");
        costumer.setTel("tel");

        mapper.add(costumer);
    }

    @Test
    @Transactional
    public void testGetCount() {
        assertEquals(1, mapper.getCount());
    }

    @Test
    @Transactional
    public void afterAddCountIsRight() {
        int count = mapper.getCount();
        Costumer costumer1 = new Costumer();
        costumer1.setUsername("costumer1");
        costumer1.setPassword("pa");
        costumer1.setTel("tel");
        mapper.add(costumer1);
        assertEquals("theCountIsNotRight", count + 1, mapper.getCount());
    }

    @Test(expected = DataIntegrityViolationException.class)
    @Transactional
    public void addSameUsername(){
        Costumer costumer1 = new Costumer();
        costumer1.setUsername("username");
        costumer1.setPassword("pas");
        costumer1.setTel("tel");
        mapper.add(costumer1);
    }

    @Test
    @Transactional
    public void addMakeCidInc() {
        int cid = costumer.getCid();
        Costumer theCostumer = new Costumer();
        theCostumer.setUsername("test");
        theCostumer.setPassword("pa");
        theCostumer.setTel("tel");
        mapper.add(theCostumer);
        assertEquals(cid + 1, theCostumer.getCid());
    }

    boolean equal(Costumer costumer1, Costumer costumer2){
        return costumer1.getUsername().equals(costumer2.getUsername())
                && costumer1.getPassword().equals(costumer2.getPassword())
                && costumer1.getAddress().equals(costumer2.getAddress())
                && costumer1.getAge() == costumer2.getAge()
                && costumer1.getRealname().equals(costumer2.getRealname())
                && costumer1.getSex() == costumer2.getSex()
                && costumer1.getTel().equals(costumer2.getTel());
    }

    @Test
    @Transactional
    public void addedDataIsRight(){
        assertTrue(equal(costumer, mapper.sel(costumer.getCid())));
    }

    @Test
    @Transactional
    public void afterDeleteTheCountIfRight(){
        int count = mapper.getCount();
        mapper.delete(costumer.getCid());
        assertEquals("deleteFailed", count - 1, mapper.getCount());
    }

    @Test
    @Transactional
    public void updatePasswordSuccess(){
        costumer.setPassword("1234");
        mapper.updatePassword(costumer);
        Costumer costumer1 = mapper.sel(costumer.getCid());
        assertEquals(costumer1.getPassword(),costumer.getPassword());
    }

    @Test
    @Transactional
    public void updateSexSuccess(){
        costumer.setSex(Costumer.FEMALE);
        mapper.updateInfo(costumer);
        Costumer costumer1 = mapper.sel(costumer.getCid());
        assertEquals(costumer1.getSex(), costumer.getSex());
    }
}
