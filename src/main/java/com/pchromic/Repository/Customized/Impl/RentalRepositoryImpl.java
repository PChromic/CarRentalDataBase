package com.pchromic.Repository.Customized.Impl;

import com.pchromic.Entity.Car;
import com.pchromic.Entity.Rental;
import com.pchromic.Entity.Rental_;
import com.pchromic.Repository.Customized.CustomizedRentalRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class RentalRepositoryImpl implements CustomizedRentalRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Car> getCarsRentedInGivenTimePeriod(LocalDate startDate, LocalDate endDate) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> cq = builder.createQuery(Car.class);
        Root<Rental> root = cq.from(Rental.class);

        // create results restriction
        List<Predicate> predicates = new ArrayList<>();
        Predicate startDateRestriction = builder.lessThan(root.get(Rental_.START_DATE),endDate);
        Predicate endDateRestriction = builder.greaterThan(root.get(Rental_.END_DATE),startDate);
        predicates.add(startDateRestriction);
        predicates.add(endDateRestriction);

        cq.select(root.get(Rental_.CAR)).distinct(true)
                .where(predicates.toArray(new Predicate[]{}));
        TypedQuery query = entityManager.createQuery(cq);
        return query.getResultList();
    }
    @Override
    public List<Long> findCarsRentedByMoreThanGivenNumberOfCustomers(Long customersNumber) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Rental> root = query.from(Rental.class);

        Path<Object> carId = root.get("car").get("id");

        query.select(builder.count(carId));
        query.groupBy(carId);
        query.having(builder.greaterThan(builder.count(carId),customersNumber));

        return entityManager.createQuery(query).getResultList();
    }
}
