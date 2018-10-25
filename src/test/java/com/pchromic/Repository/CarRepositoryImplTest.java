package com.pchromic.Repository;

import com.pchromic.Entity.Car;
import com.pchromic.Entity.EntityBuilder.CarBuilder;
import com.pchromic.Entity.Rental;
import com.pchromic.Entity.Worker;
import com.pchromic.Enum.CarMark;
import com.pchromic.Enum.CarModelType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
public class CarRepositoryImplTest {

    @Autowired
    private
    CarRepository carRepository;

    @Autowired
    private
    WorkerRepository workerRepository;

    @Autowired
    private
    RentalRepository rentalRepository;


    @Test
    public void shouldAddCar() {
        // given
        String color = "black";
        Integer distance = 1000;
        Integer engineCap = 1900;
        Integer horsepower = 147;
        CarMark carMark = CarMark.BMW;
        CarModelType carModelType = CarModelType.SEDAN;
        Integer productionYear = 2001;

        Car testCar = CarBuilder.carBuilder()
                .withColor(color)
                .withDistance(distance)
                .withEngineCapacity(engineCap)
                .withHorsepower(horsepower)
                .withMark(carMark)
                .withModelType(carModelType)
                .withProductionYear(productionYear)
                .build();

        // when
        carRepository.save(testCar);

        //then
        assertEquals(13, carRepository.findAll().size());

    }


    @Test
    public void shouldRemoveCar() {
        // given
        String color = "black";
        Integer distance = 1000;
        Integer engineCap = 1900;
        Integer horsepower = 147;
        CarMark carMark = CarMark.SKODA;
        CarModelType carModelType = CarModelType.COMBI;
        Integer productionYear = 2001;

        Car testCar = CarBuilder.carBuilder()
                .withColor(color)
                .withDistance(distance)
                .withEngineCapacity(engineCap)
                .withHorsepower(horsepower)
                .withMark(carMark)
                .withModelType(carModelType)
                .withProductionYear(productionYear)
                .build();

        testCar = carRepository.save(testCar);
        int sizeBeforeRemove = carRepository.findAll().size();

        // when
        Car carToRemove = carRepository.getOne(testCar.getId());
        carRepository.delete(carToRemove);

        int sizeAfterRemove = carRepository.findAll().size();

        assertEquals(sizeBeforeRemove-1,sizeAfterRemove);

    }

    @Test
    public void shouldRemoveCarAndRentalOtherNotChanged() {
        // given
        // -- finding worker by ID
        Worker carAdministrator = workerRepository.findById(1L).get();
        // -- finding car with given ID and adding found worker
        Car carToRemove = carRepository.findById(1L).get();
        carToRemove.addWorker(carAdministrator);

        // when
        assertEquals(1,carToRemove.getRentals().size());
        assertEquals(1,carToRemove.getCarAdministrators().size());

        int carSizeBeforeDelete = carRepository.findAll().size();
        int rentalSizeBeforeDelete = rentalRepository.findAll().size();
        int workerSizeBeforeDelete = workerRepository.findAll().size();

        // -- removing car
        carRepository.delete(carToRemove);

        int carSizeAfterDelete = carRepository.findAll().size();
        int rentalSizeAfterDelete = rentalRepository.findAll().size();
        int workerSizeAfterDelete = workerRepository.findAll().size();

        // then
        assertEquals(carSizeBeforeDelete-1,carSizeAfterDelete);
        assertEquals(rentalSizeBeforeDelete-1, rentalSizeAfterDelete);
        assertEquals(workerSizeBeforeDelete,workerSizeAfterDelete);

    }

    @Test
    public void shouldUpdateCarDetails() {

        // given
        Car carToUpdate = carRepository.findById(1L).get();
        // when
        carToUpdate.setMark(CarMark.VOLVO);
        carRepository.save(carToUpdate);
        // then
        assertEquals(carToUpdate, carRepository.findById(carToUpdate.getId()).get());

    }

    @Test
    public void shouldFindCarByMark() {

        // when
        List<Car> foundCars = carRepository.getCarByMark(CarMark.OPEL);

        // then
        assertNotNull(foundCars);
        assertFalse(foundCars.isEmpty());
        assertTrue(foundCars.stream().anyMatch(b -> b.getMark().equals(CarMark.OPEL)));
        assertEquals(3, foundCars.size());

    }


    @Test
    public void shouldFindCarByCarModelType() {

        // when
        List<Car> foundCars = carRepository.getCarByType(CarModelType.SEDAN);

        // then
        assertNotNull(foundCars);
        assertFalse(foundCars.isEmpty());
        assertTrue(foundCars.stream().anyMatch(b -> b.getModelType().equals(CarModelType.SEDAN)));
        assertEquals(4, foundCars.size());

    }

    @Test
    public void shouldFindCarByCarModelTypeAndMark() {

        // when
        List<Car> foundCars = carRepository.getCarByTypeAndMark(CarModelType.SEDAN,CarMark.OPEL);

        // then
        assertNotNull(foundCars);
        assertFalse(foundCars.isEmpty());
        assertTrue(foundCars.stream().anyMatch(b -> b.getModelType().equals(CarModelType.SEDAN)));
        assertEquals(1, foundCars.size());
    }


    @Test
    public void shouldReturnNullIfNoSuchCar() {
        // given
        long id = 16L;

        // when
        Optional<Car> car = this.carRepository.findById(id);

        // then
        assertFalse(car.isPresent());
    }

    @Test
    public void shouldGetAllCars() {

        // when
        Set<Car> carCollection = new HashSet<>();
        carCollection.addAll(carRepository.findAll());

        // then
        assertEquals(12, carCollection.size());
    }

    @Test
    @Transactional
    public void shouldAddCarAdministrator() {
        // given
        Car testCar = carRepository.findById(12L).get();
        Worker testWorker = workerRepository.findById(3L).get();

        // when
        carRepository.addAdministratorToCar(testCar.getId(), testWorker.getId());

        // then
        assertFalse(testCar.getCarAdministrators().isEmpty());
        assertEquals(1,testCar.getCarAdministrators().size());
        assertFalse(workerRepository.findById(testWorker.getId()).get().getCars().isEmpty());

    }

    @Test
    public void shouldGetCarsByCarAdministrator() {

        // given
        Car testCar = carRepository.findById(12L).get();
        Worker testWorker = workerRepository.findById(3L).get();
        carRepository.addAdministratorToCar(testCar.getId(), testWorker.getId());

        // when
        List<Car> carCollection = new ArrayList<>();
        carCollection.addAll(carRepository.getCarByCarAdministrator(testWorker.getId()));

        // then
        assertEquals(1, carCollection.size());

    }

    @Test
    public void shouldAddRentalToCar() {

        // given
        Rental testRental = rentalRepository.findById(15L).get();
        Car testCar = carRepository.findById(7L).get();

        // when
        carRepository.addRentalToCar(testCar.getId(),testRental);

        // then
        assertEquals(rentalRepository.findById(15L).get().getCar(),carRepository.findById(7L).get());
    }

    @Test
    public void shouldRemoveRentalFromCar() {

        // given
        Rental testRental = rentalRepository.findById(14L).get();
        Car testCar = carRepository.findById(7L).get();

        // when
        carRepository.removeRentalFromCar(testCar.getId(),testRental);

        // then
        assertEquals(rentalRepository.findById(14L).get().getCar(),carRepository.findById(7L).get());
    }



}