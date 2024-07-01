package com.jobdekhodotcom.companyms.company.services;

import java.util.List;
import java.util.UUID;

import com.jobdekhodotcom.companyms.company.dto.ReviewMessage;
import com.jobdekhodotcom.companyms.company.entity.Company;

public interface CompanyService {
  List<Company> getAllCompanies();
  void createCompany(Company company);
  Company updateCompany(Company company, UUID id);
  boolean deleteCompanyById(UUID id);
  Company getCompanyById(UUID id);
  public void updateCompanyRating(ReviewMessage reviewMessage);
}
