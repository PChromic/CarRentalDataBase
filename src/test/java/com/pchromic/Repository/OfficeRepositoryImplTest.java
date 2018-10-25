package com.pchromic.Repository;


import com.pchromic.Entity.Car;
import com.pchromic.Entity.EntityBuilder.WorkerBuilder;
import com.pchromic.Entity.Office;
import com.pchromic.Entity.Worker;
import com.pchromic.Enum.WorkerPosition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OfficeRepositoryImplTest {

    @Autowired
    private
    OfficeRepository officeRepository;

    @Autowired
    private
    WorkerRepository workerRepository;

    @Autowired
    private
    CarRepository carRepository;

    @Test
    public void shouldRemoveOffice() {
        // given
        Office testOffice = officeRepository.findById(1L).get();
        // when
        officeRepository.delete(testOffice);
        // then
        assertEquals(2,officeRepository.count());
    }

    @Test
    public void shouldAddWorkerToOffice( ) {
        // given
        Worker testWorker = WorkerBuilder.workerBuilder()
                .withBirthDate(LocalDate.of(2000, 1, 2))
                .withWorkerPosition(WorkerPosition.MANAGER)
                .withFirstName("Ryszard")
                .withLastName("Kaputsta")
                .build();
        workerRepository.save(testWorker);

        Office testOffice = officeRepository.findById(1L).get();

        // when
        assertNull(testWorker.getOffice());

        officeRepository.addWorkerToOffice(testOffice.getId(),testWorker.getId());

        // then
        assertFalse(testOffice.getOfficeWorkers().isEmpty());
        assertEquals(4,testOffice.getOfficeWorkers().size());
        assertTrue(workerRepository.getWorkersByOffice(1L).contains(testWorker));
        assertNotNull(workerRepository.findById(testWorker.getId()).get().getOffice());
    }

    @Test
    public void shouldRemoveWorkerFromOffice( ) {
        // given

        Office testOffice = officeRepository.findById(1L).get();

        int officeWorkerAmountBeforeRemove = testOffice.getOfficeWorkers().size();
        int workerAmountBeforeRemove = workerRepository.findAll().size();

        Worker workerRemoved = testOffice.getOfficeWorkers().get(1);

        // when
        officeRepository.removeWorkerFromOffice(testOffice.getId(),workerRemoved.getId());

        int officeWorkerAmountAfterRemove = testOffice.getOfficeWorkers().size();
        int workerAmountAfterRemove = workerRepository.findAll().size();

        // then
        assertFalse(testOffice.getOfficeWorkers().isEmpty());
        assertEquals(officeWorkerAmountBeforeRemove-1,officeWorkerAmountAfterRemove);
        assertEquals(workerAmountBeforeRemove-1,workerAmountAfterRemove);

    }

    @Test
    public void shouldFindOfficeWorkersWhoTakeCareOfGivenCar() {
        // given
        Office testOffice = officeRepository.findById(1L).get();
        Worker testWorker = workerRepository.findById(3L).get();
        Worker testWorker2 = workerRepository.findById(6L).get();
        Car testCar = carRepository.findById(12L).get();
        Car testCar2 = carRepository.findById(11L).get();

        testCar.addWorker(testWorker);
        testCar.addWorker(testWorker2);
        testCar2.addWorker(testWorker);

        List<Worker> workerList = workerRepository.getWorkersByOfficeWhoCareOfGivenCar(testOffice.getId(), testCar.getId());

        // when
        assertFalse(workerList.isEmpty());
        assertEquals(testWorker,workerList.get(0));

    }

}

