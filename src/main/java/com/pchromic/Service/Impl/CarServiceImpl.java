package com.pchromic.Service.Impl;

import com.pchromic.Entity.Car;
import com.pchromic.Enum.CarMark;
import com.pchromic.Enum.CarModelType;
import com.pchromic.Mappers.CarMapper;
import com.pchromic.Repository.CarRepository;
import com.pchromic.Repository.WorkerRepository;
import com.pchromic.Service.CarService;
import com.pchromic.TO.CarTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private
    CarRepository repository;

    @Autowired
    private
    WorkerRepository workerRepository;

    @Autowired
    private CarMapper mapper;

    @Override
    public void addCar(CarTO car) {

       repository.save(mapper.toEntity(car));

    }

    @Override
    public void removeCar(Long carId) {

        repository.deleteById(carId);
    }

    @Override
    public void updateCarDetails(CarTO car) {

        repository.save(mapper.toEntity(car));
    }

    @Override
    public CarTO getCarById(Long carId) {
        Optional<Car> foundOptional = repository.findById(carId);
        if (foundOptional.isPresent()) {
            foundOptional.get().setCarAdministrators(workerRepository.getWorkerByCar(carId));
            return mapper.toCarTO(foundOptional.get());
        }
        return null;
    }

    @Override
    public List<CarTO> getCarByMark(CarMark mark) {

        return mapper.map2TOs(repository.getCarByMark(mark));
    }

    @Override
    public List<CarTO> getCarByType(CarModelType modelType) {

        return mapper.map2TOs(repository.getCarByType(modelType));
    }

    @Override
    public List<CarTO> getCarsByCarAdministrator(Long administratorId) {

        return mapper.map2TOs(repository.getCarByCarAdministrator(administratorId));
    }

    @Override
    public void addAdministratorToCar(Long carId, Long administratorId) {
        repository.addAdministratorToCar(carId,administratorId);
    }
}
