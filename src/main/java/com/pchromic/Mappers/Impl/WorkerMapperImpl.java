package com.pchromic.Mappers.Impl;

import com.pchromic.Entity.Worker;
import com.pchromic.Mappers.WorkerMapper;
import com.pchromic.TO.TOBuilder.WorkerTOBuilder;
import com.pchromic.TO.WorkerTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkerMapperImpl implements WorkerMapper {

    @Override
    public WorkerTO toWorkerTO(Worker worker) {
        if (worker == null)
            return null;
        return  WorkerTOBuilder.aWorkerTO()
                .withBirthDate(worker.getBirthDate())
                .withFirstName(worker.getFirstName())
                .withLastName(worker.getLastName())
                .withWorkerPosition(worker.getWorkerPosition())
                .withId(worker.getId())
                .build();
    }

    @Override
    public Worker toWorkerEntity(WorkerTO workerTO) {
        if (workerTO == null)
            return null;
        Worker worker = new Worker();
        worker.setFirstName(workerTO.getFirstName());
        worker.setLastName(workerTO.getLastName());
        worker.setBirthDate(workerTO.getBirthDate());
        worker.setWorkerPosition(workerTO.getWorkerPosition());
        return worker;
    }

    @Override
    public List<WorkerTO> map2TOs(List<Worker> workerEntities) {
        return workerEntities.stream().map(this::toWorkerTO).collect(Collectors.toList());
    }

    @Override
    public List<Worker> map2Entities(List<WorkerTO> WorkerTOs) {
        return WorkerTOs.stream().map(this::toWorkerEntity).collect(Collectors.toList());
    }

}
