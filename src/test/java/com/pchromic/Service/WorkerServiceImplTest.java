package com.pchromic.Service;

import com.pchromic.Entity.Worker;
import com.pchromic.Repository.WorkerRepository;
import com.pchromic.TO.WorkerTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class WorkerServiceImplTest {

    @Autowired
    private
    WorkerService service;
    @Autowired
    private
    WorkerRepository repository;

   @Test
    public void shouldGetWorkersById(){
        // when
        Worker testWorker = repository.findById(1L).get();
        WorkerTO testWorkerTO = service.getWorkerById(1L);

        // then
 
    }

}
