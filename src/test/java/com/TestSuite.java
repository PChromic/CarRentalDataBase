package com;

import com.pchromic.Repository.*;
import com.pchromic.Service.CarServiceImplTest;
import com.pchromic.Service.OfficeServiceImplTest;
import com.pchromic.Service.WorkerServiceImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CarRepositoryImplTest.class,
        OfficeRepositoryImplTest.class,
        RentalRepositoryImplTest.class,
        WorkerRepositoryImplTest.class,
        CustomerRepositoryImplTest.class,
        CarServiceImplTest.class,
        OfficeServiceImplTest.class,
        WorkerServiceImplTest.class
})
public class TestSuite {
}
