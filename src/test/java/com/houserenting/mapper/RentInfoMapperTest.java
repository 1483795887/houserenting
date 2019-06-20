package com.houserenting.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentInfoMapperTest {
    @Autowired
    RentInfoMapper rentInfoMapper;

    @Test
    public void selRandomWhenNotDatabaseIsEmpty() {
        assertNull(rentInfoMapper.sel(100));
    }
}
