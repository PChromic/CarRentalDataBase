package com.pchromic.Repository;

import com.pchromic.Entity.Office;
import com.pchromic.Repository.Customized.CustomizedOfficeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<Office, Long>, CustomizedOfficeRepository {



}
