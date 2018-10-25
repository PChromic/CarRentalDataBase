package com.pchromic.Service.Impl;

import com.WorkerSearchCriteria;
import com.pchromic.Entity.Worker;
import com.pchromic.Mappers.WorkerMapper;
import com.pchromic.Repository.WorkerRepository;
import com.pchromic.Service.WorkerService;
import com.pchromic.TO.WorkerTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private
    WorkerRepository repository;

    @Autowired
    private
    WorkerMapper mapper;

    @Override
    public WorkerTO getWorkerById(Long workerId) {
        Optional<Worker> foundOptional = repository.findById(workerId);
        if (foundOptional.isPresent())
            return mapper.toWorkerTO(foundOptional.get());
        return null;
    }

    @Override
    public Worker addWorker(WorkerTO worker) {
        return repository.save(mapper.toWorkerEntity(worker));
    }

    @Override
    public void removeWorker(Long workerId) {
        repository.deleteById(workerId);

    }

    @Override
    public void removeWorker(WorkerTO worker) {
        repository.delete(mapper.toWorkerEntity(worker));

    }

    @Override
    public void updateWorkerDetails(WorkerTO worker) {
        repository.save(mapper.toWorkerEntity(worker));

    }

    @Override
    public List<WorkerTO> getAllWorkersByOffice(Long officeId) {
        List<Worker> workers = repository.getWorkersByOffice(officeId);
        return mapper.map2TOs(workers);
    }

    @Override
    public List<WorkerTO> getAllOfficeWorkersByCar(Long officeId, Long carId) {
        List<Worker> workers = repository.getWorkersByOfficeWhoCareOfGivenCar(officeId,carId);
        return mapper.map2TOs(workers);
    }

    @Override
    public List<WorkerTO> getWorkersByCriteria(WorkerSearchCriteria criteria) {
        List <Worker> workers = repository.getWorkerByCriteria(criteria);
        return mapper.map2TOs(workers);
    }
}
