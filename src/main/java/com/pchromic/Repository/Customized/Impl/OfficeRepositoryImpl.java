package com.pchromic.Repository.Customized.Impl;

import com.pchromic.Entity.Office;
import com.pchromic.Entity.Worker;
import com.pchromic.Repository.Customized.CustomizedOfficeRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class OfficeRepositoryImpl implements CustomizedOfficeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override

    public void addWorkerToOffice(Long officeId, Long workerId) {
        Worker officeWorker = entityManager.find(Worker.class,workerId);
        Office foundOffice = entityManager.find(Office.class,officeId);
        foundOffice.addWorker(officeWorker);
        entityManager.persist(foundOffice);

    }

    @Override
    public void removeWorkerFromOffice(Long officeId, Long workerId) {
        Office foundOffice = entityManager.find(Office.class,officeId);
        Worker removedWorker = entityManager.find(Worker.class, workerId);
        foundOffice.removeWorker(removedWorker);

    }


}
