package com.pchromic.Repository;

import com.pchromic.Entity.Worker;
import com.pchromic.Repository.Customized.CustomizedWorkerRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker,Long>, CustomizedWorkerRepository {



}
