package com.pchromic.Repository;

import com.pchromic.Entity.Rental;
import com.pchromic.Repository.Customized.CustomizedRentalRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental,Long>, CustomizedRentalRepository {

}
