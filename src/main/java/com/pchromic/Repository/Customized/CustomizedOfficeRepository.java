package com.pchromic.Repository.Customized;

public interface CustomizedOfficeRepository {

    void addWorkerToOffice(Long officeId, Long workerId);

    void removeWorkerFromOffice (Long officeId, Long workerId);


}
