package com.pchromic.Repository;

import com.pchromic.Entity.Car;
import com.pchromic.Repository.Customized.CustomizedCarRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long>, CustomizedCarRepository {


}
