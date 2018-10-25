package com.pchromic.Repository.Customized.Impl;

import com.WorkerSearchCriteria;
import com.pchromic.Entity.*;
import com.pchromic.Repository.Customized.CustomizedWorkerRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Repository
@Transactional
public class WorkerRepositoryImpl implements CustomizedWorkerRepository {

    @PersistenceContext
    private
    EntityManager entityManager;


    @Override
    public List<Worker> getWorkerByCriteria(WorkerSearchCriteria criteria) {

        CriteriaBuilder cb =
                entityManager.getCriteriaBuilder();

        CriteriaQuery<Worker> query =
                cb.createQuery(Worker.class);

        Root<Worker> root =
                query.from(Worker.class);

        List<Predicate> predicates = new ArrayList<>();

        if(criteria.getOfficeId() != null){
            predicates.add(cb.equal(root.get("office").get("id"),criteria.getOfficeId()));
        }

        if (criteria.getCarId() != null) {
            Join<Worker,Car> workerCars = root.join(Worker_.CARS);
            Predicate carRestriction = cb.equal(workerCars.get(Car_.ID),criteria.getCarId());
            predicates.add(carRestriction);
        }

        if (criteria.getWorkerPosition() != null) {
            predicates.add(cb.equal(root.get("workerPosition"),criteria.getWorkerPosition()));
        }

        query.where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Worker> getWorkerByCar(Long carId) {
        CriteriaBuilder cb =
                entityManager.getCriteriaBuilder();

        CriteriaQuery<Worker> query =
                cb.createQuery(Worker.class);

        Root<Worker> root =
                query.from(Worker.class);

        Join<Worker,Car> workerCars = root.join(Worker_.CARS);

        Predicate carRestriction = cb.equal(workerCars.get(Car_.ID), carId);

        query.where(carRestriction);

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Worker> getWorkersByOffice(long officeId) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Office> query = cb.createQuery(Office.class);
        Root<Office> office = query.from(Office.class);
        query.select(office).where(cb.equal(office.get("id"),officeId));
        TypedQuery<Office> typedQuery = entityManager.createQuery(query);
        Office foundOffice = typedQuery.getSingleResult();
        return foundOffice.getOfficeWorkers();
    }

    @Override
    public List<Worker> getWorkersByOfficeWhoCareOfGivenCar(Long officeId, Long carId) {

        // create the outer query
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Worker> cq = cb.createQuery(Worker.class);
        Root<Worker> root = cq.from(Worker.class);
        Join <Worker,Car> workerCars = root.join(Worker_.CARS);
        Join <Worker,Office> workerOffices = root.join(Worker_.OFFICE);

        // create results restriction
        List<Predicate> predicates = new ArrayList<>();
        Predicate carRestriction = cb.equal(workerCars.get(Car_.ID),carId);
        Predicate officeRestriction = cb.equal(workerOffices.get(Office_.ID),officeId);
        predicates.add(carRestriction);
        predicates.add(officeRestriction);

        cq.select(root).where(predicates.toArray(new Predicate[]{}));

        TypedQuery query = entityManager.createQuery(cq);

        return query.getResultList();
    }

}

