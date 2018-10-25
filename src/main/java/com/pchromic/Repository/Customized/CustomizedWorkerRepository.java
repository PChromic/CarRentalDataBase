package com.pchromic.Repository.Customized;

import com.WorkerSearchCriteria;
import com.pchromic.Entity.Worker;

import java.util.List;

public interface CustomizedWorkerRepository {
    List<Worker> getWorkerByCriteria(WorkerSearchCriteria criteria);
    List<Worker> getWorkerByCar (Long carId);
    List<Worker> getWorkersByOffice(long officeId);
    List<Worker> getWorkersByOfficeWhoCareOfGivenCar(Long officeId, Long carId);
}
