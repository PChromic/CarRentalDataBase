package com.pchromic.Mappers;

import com.pchromic.Entity.Worker;
import com.pchromic.TO.WorkerTO;

import java.util.List;

public interface WorkerMapper {
    List<WorkerTO> map2TOs(List<Worker> workerEntities);

    List<Worker> map2Entities(List<WorkerTO> WorkerTOs);

    WorkerTO toWorkerTO(Worker worker);

    Worker toWorkerEntity (WorkerTO workerTO);
}
