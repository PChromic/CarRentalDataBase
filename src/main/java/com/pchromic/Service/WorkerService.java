package com.pchromic.Service;

import com.WorkerSearchCriteria;
import com.pchromic.Entity.Worker;
import com.pchromic.TO.WorkerTO;

import java.util.List;

public interface WorkerService {

    Worker addWorker(WorkerTO worker);

    void removeWorker (Long workerId);

    void removeWorker(WorkerTO worker);

    WorkerTO getWorkerById (Long workerId);

    void updateWorkerDetails (WorkerTO worker);

    List<WorkerTO> getAllWorkersByOffice(Long officeId);

    List<WorkerTO> getAllOfficeWorkersByCar(Long officeId, Long carId);

    List<WorkerTO> getWorkersByCriteria (WorkerSearchCriteria criteria);
}
