package com.jobdekhodotcom.companyms.company.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jobdekhodotcom.companyms.company.clients.ReviewClient;
import com.jobdekhodotcom.companyms.company.dto.ReviewMessage;
import com.jobdekhodotcom.companyms.company.entity.Company;
import com.jobdekhodotcom.companyms.company.repository.CompanyRepository;

@Service
public class CompanyServicImpl implements CompanyService{

  private CompanyRepository companyRepository;
  private ReviewClient reviewClient;

  public CompanyServicImpl(CompanyRepository companyRepository, ReviewClient reviewClient) {
    this.companyRepository = companyRepository;
    this.reviewClient = reviewClient;
  }

  @Override
  public List<Company> getAllCompanies() {
    return companyRepository.findAll();
  }

  @Override
  public Company updateCompany(Company company, UUID id) {
    Optional<Company> companyOptional = companyRepository.findById(id);
    if(companyOptional.isPresent()){
      Company companyToUpdate = companyOptional.get();
      companyToUpdate.setName(company.getName());
      companyToUpdate.setDescription(company.getDescription());
      companyRepository.save(companyToUpdate);
      return companyToUpdate;
    }
    return null;
  }

  @Override
  public void createCompany(Company company) {
    companyRepository.save(company);
  }

  @Override
  public boolean deleteCompanyById(UUID id) {
    if (companyRepository.existsById(id)) {
      companyRepository.deleteById(id);
      return true;
    }
    return false;
  }

  @Override
  public Company getCompanyById(UUID id) {
    return companyRepository.findById(id).orElse(null);
  }

  @Override
  public void updateCompanyRating(ReviewMessage reviewMessage) {
    Company company = companyRepository.findById(reviewMessage.getCompanyId()).orElseThrow(() -> new RuntimeException("Company not found"));

    double avgRating = reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());
    company.setRating(avgRating);
    companyRepository.save(company);
  }
  
}
