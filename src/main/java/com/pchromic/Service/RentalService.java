package com.pchromic.Service;

import com.pchromic.TO.RentalTO;

public interface RentalService {

    void addRental(RentalTO rentalTO);

    void removeRental(Long rentalId);

    void updateRentalDetails(RentalTO rentalTO);

    void getRentalById (Long rentalId);

}
