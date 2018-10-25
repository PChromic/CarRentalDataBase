package com.pchromic.Mappers;

import com.pchromic.Entity.Office;
import com.pchromic.TO.OfficeTO;

public interface OfficeMapper {
    Office toOfficeEntity (OfficeTO officeTO);

    OfficeTO toOfficeTO(Office office);
}
