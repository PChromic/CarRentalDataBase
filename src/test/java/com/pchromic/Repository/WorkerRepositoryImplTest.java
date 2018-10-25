package com.pchromic.Repository;

import com.WorkerSearchCriteria;
import com.pchromic.Entity.Car;
import com.pchromic.Entity.EntityBuilder.WorkerBuilder;
import com.pchromic.Entity.Office;
import com.pchromic.Entity.Worker;
import com.pchromic.Enum.WorkerPosition;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class WorkerRepositoryImplTest {

    private static Validator validator;

    @Autowired
    private
    CarRepository carRepository;

    @Autowired
    private
    WorkerRepository workerRepository;
    @Autowired
    private
    OfficeRepository officeRepository;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldFindWorkerByCar() {
        // given
        Car testCar = carRepository.findById(2L).get();
        Car testCar2 = carRepository.findById(3L).get();

        Worker testWorker = workerRepository.findById(1L).get();

        testCar.addWorker(testWorker);
        testCar2.addWorker(testWorker);

        // when
        WorkerSearchCriteria criteria = WorkerSearchCriteria.builder().withCarId(testCar.getId()).build();

        List<Worker> foundWorkers = workerRepository.getWorkerByCriteria(criteria);

        // then
        assertFalse(foundWorkers.isEmpty());
        assertEquals(1,foundWorkers.size());
        assertEquals(testWorker,foundWorkers.get(0));
    }

   @Test
    public void shouldFindWorkerByWorkerPosition() {
        // given
       WorkerPosition workerPosition = WorkerPosition.MANAGER;
       Worker managerOffice1 = workerRepository.findById(2L).get();
       Worker managerOffice2 = workerRepository.findById(5L).get();
       Worker managerOffice3 = workerRepository.findById(8L).get();
        // when
        WorkerSearchCriteria criteria = WorkerSearchCriteria.builder().withWorkerPosition(workerPosition).build();

        List<Worker> foundWorkers = workerRepository.getWorkerByCriteria(criteria);

        // then
        assertFalse(foundWorkers.isEmpty());
        assertEquals(3,foundWorkers.size());
        assertTrue(foundWorkers.contains(managerOffice1));
        assertTrue(foundWorkers.contains(managerOffice2));
        assertTrue(foundWorkers.contains(managerOffice3));
    }


    @Test
    public void shouldFindWorkerByOffice(){
        Office testOffice = officeRepository.findById(1L).get();

        // when
        WorkerSearchCriteria criteria = WorkerSearchCriteria.builder().withOfficeId(testOffice.getId()).build();

        List<Worker> foundWorkers = workerRepository.getWorkerByCriteria(criteria);

        // then
        assertFalse(foundWorkers.isEmpty());
        assertEquals(3,foundWorkers.size());
        assertNotNull(foundWorkers.get(0));
        assertNotNull(foundWorkers.get(1));
        assertNotNull(foundWorkers.get(2));
    }

    // ----------------------- VALIDATION TESTS ----------------------- //
    @Test
    public void shouldDetectConstraintViolationIfBirthDateAheadOfTime(){
        // given
        Worker worker = WorkerBuilder.workerBuilder()
                .withWorkerPosition(WorkerPosition.MANAGER)
                .withFirstName("Prr")
                .withLastName("Ws")
                .withBirthDate(LocalDate.of(2222,02,02))
                .build();

        // when
        Set<ConstraintViolation<Worker>> constraintViolations =
                validator.validate( worker );

        // then
        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "must be a past date",
                constraintViolations.iterator().next().getMessage()
        );
    }

}
