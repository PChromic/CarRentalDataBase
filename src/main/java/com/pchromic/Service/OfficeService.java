package com.pchromic.Service;

import com.pchromic.TO.OfficeTO;

public interface OfficeService {

    void addOffice(OfficeTO office);

    void removeOffice(Long officeId);

    void updateOfficeDetails(OfficeTO office);

    void addWorkerToOffice(Long officeId, Long workerId);

    void removeWorkerFromOffice(Long officeId, Long workerId);

    OfficeTO getOfficeById (long officeId);


}
