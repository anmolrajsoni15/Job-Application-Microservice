package com.jobdekhodotcom.jobms.job.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobdekhodotcom.jobms.job.entity.Job;

public interface JobRepository extends JpaRepository<Job, UUID>{
  
}
