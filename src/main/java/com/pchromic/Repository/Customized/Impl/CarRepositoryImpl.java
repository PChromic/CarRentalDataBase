package com.pchromic.Repository.Customized.Impl;

import com.pchromic.Entity.Car;
import com.pchromic.Entity.Rental;
import com.pchromic.Entity.Worker;
import com.pchromic.Enum.CarMark;
import com.pchromic.Enum.CarModelType;
import com.pchromic.Repository.Customized.CustomizedCarRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class CarRepositoryImpl implements CustomizedCarRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Car> getCarByMark(CarMark mark) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> query = builder.createQuery(Car.class);

        Root<Car> car = query.from(Car.class);
        query.select(car)
                .where(builder.equal(car.get("mark"), mark));

        TypedQuery<Car> tq = entityManager.createQuery(query);
        return tq.getResultList();

    }

    @Override
    public List<Car> getCarByType(CarModelType type) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> query = builder.createQuery(Car.class);

        Root<Car> car = query.from(Car.class);
        query.select(car)
                .where(builder.equal(car.get("modelType"), type));

        TypedQuery<Car> tq = entityManager.createQuery(query);
        return tq.getResultList();


    }
    @Override
    public List<Car> getCarByTypeAndMark(CarModelType modelType, CarMark carMark ) {

        CriteriaBuilder cb =
                entityManager.getCriteriaBuilder();

        CriteriaQuery<Car> query =
                cb.createQuery(Car.class);

        Root<Car> root =
                query.from(Car.class);

        List<Predicate> predicates = new ArrayList<>();

        if(modelType != null){
            Predicate modelTypePredicate = cb.equal(root.get("modelType"),modelType);
            predicates.add(modelTypePredicate);
        }

        if (carMark != null) {
            Predicate carMarkPredicate = cb.equal(root.get("mark"),carMark);
            predicates.add(carMarkPredicate);
        }


        query.where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Car> getCarByCarAdministrator(Long administratorId) {

        return new ArrayList<>(entityManager.find(Worker.class,administratorId).getCars());
    }

    @Override
    public void addAdministratorToCar(Long carId, Long administratorId) {

        Car carToAdministrate = entityManager.find(Car.class,carId);
        Worker administrator = entityManager.find(Worker.class,administratorId);
        carToAdministrate.addWorker(administrator);

        entityManager.merge(carToAdministrate);

    }

    @Override
    public void addRentalToCar(Long carId, Rental rental) {
        Car carToAddRental = entityManager.find(Car.class,carId);
        carToAddRental.addRental(rental);
        rental.setCar(carToAddRental);

        entityManager.merge(carToAddRental);

    }

    @Override
    public Car removeRentalFromCar(Long carId, Rental rental) {
        Car carToRemoveRental = entityManager.find(Car.class,carId);
        carToRemoveRental.removeRental(rental);
        rental.setCar(carToRemoveRental);

        entityManager.merge(carToRemoveRental);
        return carToRemoveRental;
    }
}