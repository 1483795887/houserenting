package com.houserenting;

import com.houserenting.mapper.AdminMapperTest;
import com.houserenting.mapper.CustomerMapperTest;
import com.houserenting.mapper.RentInfoMapperTest;
import com.houserenting.service.AdminServiceTest;
import com.houserenting.service.CustomerServiceTest;
import com.houserenting.service.RentInfoServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AdminMapperTest.class,
        CustomerMapperTest.class,
        RentInfoMapperTest.class,

        AdminServiceTest.class,
        CustomerServiceTest.class,
        RentInfoServiceTest.class
})
@SpringBootTest
public class HouserentingApplicationTests {

}
