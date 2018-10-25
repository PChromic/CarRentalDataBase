
package com.pchromic.Service.Impl;

import com.pchromic.Entity.Office;
import com.pchromic.Mappers.OfficeMapper;
import com.pchromic.Mappers.WorkerMapper;
import com.pchromic.Repository.OfficeRepository;
import com.pchromic.Repository.WorkerRepository;
import com.pchromic.Service.OfficeService;
import com.pchromic.TO.OfficeTO;
import com.pchromic.TO.WorkerTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    private
    OfficeRepository officeRepository;

    @Autowired
    private
    WorkerRepository workerRepository;

    @Autowired
    private OfficeMapper mapper;

    @Autowired
    private WorkerMapper workerMapper;


    @Override
    public void addOffice(OfficeTO office) {
        officeRepository.save(mapper.toOfficeEntity(office));
    }

    @Override
    public void removeOffice(Long officeId) {
        officeRepository.deleteById(officeId);
    }

    @Override
    public void updateOfficeDetails(OfficeTO office) {
        officeRepository.save(mapper.toOfficeEntity(office));
    }

    @Override
    public void addWorkerToOffice(Long officeId, Long workerId) {
        officeRepository.addWorkerToOffice(officeId,workerId);
    }

    @Override
    public void removeWorkerFromOffice(Long officeId, Long workerId) {
        officeRepository.removeWorkerFromOffice(officeId, workerId);
    }

    @Override
    public OfficeTO getOfficeById(long officeId) {
        Optional<Office> foundOffice = officeRepository.findById(officeId);
        if (foundOffice.isPresent()) {
            OfficeTO foundOfficeTO = mapper.toOfficeTO(foundOffice.get());
            List<WorkerTO> foundWorkers = workerMapper.map2TOs(workerRepository.getWorkersByOffice(officeId));
            foundOfficeTO.setWorkerTOList(foundWorkers);
            return foundOfficeTO;
        }
        return null;
    }


}

