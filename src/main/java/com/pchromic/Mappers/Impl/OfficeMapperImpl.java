
package com.pchromic.Mappers.Impl;

import com.pchromic.Entity.Office;
import com.pchromic.Mappers.OfficeMapper;
import com.pchromic.TO.OfficeTO;
import org.springframework.stereotype.Component;

@Component
public class OfficeMapperImpl implements OfficeMapper {

    @Override
    public Office toOfficeEntity(OfficeTO officeTO) {
        if (officeTO == null)
            return null;


        Office office = new Office();
        office.setId(officeTO.getId());
        office.setAddress(officeTO.getAddress());
        office.setEmail(officeTO.getEmail());
        office.setPhoneNumber(officeTO.getPhoneNumber());
        return office;
    }

    @Override
    public OfficeTO toOfficeTO(Office office){
        if (office == null)
            return null;

        OfficeTO officeTO = new OfficeTO();
        officeTO.setAddress(office.getAddress());
        officeTO.setEmail(office.getEmail());
        officeTO.setPhoneNumber(office.getPhoneNumber());
        officeTO.setId(office.getId());
        officeTO.setDeleted(office.isDeleted());
        return officeTO;
    }


}

