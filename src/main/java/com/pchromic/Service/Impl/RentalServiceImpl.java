package com.pchromic.Service.Impl;

import com.pchromic.Repository.RentalRepository;
import com.pchromic.Service.RentalService;
import com.pchromic.TO.RentalTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    RentalRepository repository;

    @Override
    public void addRental(RentalTO rentalTO) {


    }

    @Override
    public void removeRental(Long rentalId) {

    }

    @Override
    public void updateRentalDetails(RentalTO rentalTO) {

    }

    @Override
    public void getRentalById(Long rentalId) {

    }
}
