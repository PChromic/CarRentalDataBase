package com.pchromic.Repository;

import com.pchromic.Entity.Car;
import com.pchromic.Entity.Rental;
import com.pchromic.Entity.Worker;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
public class RentalRepositoryImplTest {

    private static Validator validator;

    @Autowired
    private
    RentalRepository repository;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldSetConstraintValidationWhenEndDateBeforeStartDate () {
        // given
        LocalDate startDate = LocalDate.of(2000, 12, 9);
        LocalDate endDate = LocalDate.of(2000, 11, 20);

        // when
        Rental rental = repository.findById(1L).get();
        rental.setStartDate(startDate);
        rental.setEndDate(endDate);

        // then
        Set<ConstraintViolation<Rental>> constraintViolations =
                validator.validate( rental );
        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "End date ["+ endDate +"] has to be after start date ["
                        + startDate +"] or even!",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void shouldGetCarsRentedOverGivenTimePeriod () {
        // given
        LocalDate startDate = LocalDate.of(2000, 1, 9);
        LocalDate endDate = LocalDate.of(2000, 1, 20);
        // when
        List<Car> foundCars = repository.getCarsRentedInGivenTimePeriod(startDate, endDate);
        // then
        assertFalse(foundCars.isEmpty());
        assertEquals(4,foundCars.size());
    }
    @Test
    public void shouldGetCarsRentedMoreThanGivenTimes() {
        // given
        List<Rental> rentals = repository.findAll();

        // when
        List<Long> foundCars = repository.findCarsRentedByMoreThanGivenNumberOfCustomers(4L);

        // then
        assertFalse(rentals.isEmpty());
        assertEquals(2, foundCars.size());

    }

}
