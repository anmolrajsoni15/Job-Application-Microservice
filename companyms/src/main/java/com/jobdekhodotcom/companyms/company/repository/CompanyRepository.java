package com.jobdekhodotcom.companyms.company.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobdekhodotcom.companyms.company.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, UUID>{
  
}
