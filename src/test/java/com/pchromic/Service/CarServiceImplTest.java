package com.pchromic.Service;


import com.pchromic.Entity.Car;
import com.pchromic.Enum.CarMark;
import com.pchromic.Enum.CarModelType;
import com.pchromic.Repository.CarRepository;
import com.pchromic.TO.CarTO;
import com.pchromic.TO.TOBuilder.CarTOBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class CarServiceImplTest {

    @Autowired
    private
    CarRepository carRepository;

    @Autowired
    WorkerService workerService;
    @Autowired
    private
    CarService carService;

    @Test
    public void shouldAddNewCar() {
        // given
        CarTO newCar = CarTOBuilder.aCarTO()
                .withColor("Blue")
                .withDistance(1859)
                .withEngineCapacity(1200)
                .withHorsepower(100)
                .withMark(CarMark.OPEL)
                .withModelType(CarModelType.COMBI)
                .withProductionYear(2002)
                .withWorkers(new ArrayList<>())
                .build();

        //when
        carService.addCar(newCar);

        //then
        assertEquals(13, carRepository.findAll().size());

    }

    @Test
    public void shouldRemoveCar() {
        // given
        CarTO carTO = carService.getCarById(1L);

        //when

        carService.removeCar(carTO.getId());

        //then
        assertEquals(11, carRepository.findAll().size());

    }

    @Test
    public void shouldUpdateCarDetails() {
        // given
        CarTO testCarTO = carService.getCarById(1L);

        testCarTO.setMark(CarMark.BMW);

        //when
        carService.updateCarDetails(testCarTO);
        List<Car> cars = carRepository.findAll();

        //then
        assertEquals(12,cars.size());
        assertEquals(4, carRepository.getCarByMark(CarMark.BMW).size());
    }


    @Test
    public void shouldGetCarByMark() {
        //when
        List<CarTO> carByMark = carService.getCarByMark(CarMark.OPEL);

        //then
        assertFalse(carByMark.isEmpty());
        assertEquals(3, carByMark.size());
        assertEquals(CarMark.OPEL, carByMark.get(0).getMark());
        assertEquals(CarMark.OPEL, carByMark.get(1).getMark());
        assertEquals(CarMark.OPEL, carByMark.get(2).getMark());
    }

    @Test
    public void shouldGetCarByType() {
        //when
        List<CarTO> carByType = carService.getCarByType(CarModelType.COMBI);

        //then
        assertFalse(carByType.isEmpty());
        assertEquals(4, carByType.size());
        assertEquals(CarModelType.COMBI, carByType.get(0).getModelType());
        assertEquals(CarModelType.COMBI, carByType.get(1).getModelType());
        assertEquals(CarModelType.COMBI, carByType.get(2).getModelType());
        assertEquals(CarModelType.COMBI, carByType.get(3).getModelType());

    }
// STACK OVERFLOW
  /*  @Test
    public void shouldAddAdministratorToCar() {
        //given
        CarTO testCar = carService.getCarById(1L);
        WorkerTO testWorker = workerService.getWorkerById(1L);

        //when
        carService.addAdministratorToCar(testCar.getId(), testWorker.getId());
        testCar = carService.getCarById(1L);
        testWorker = workerService.getWorkerById(1L);

        //then
        assertEquals(1,testCar.getWorkers().size());
        assertEquals(1,testWorker.getCars().size());
    }*/
}
