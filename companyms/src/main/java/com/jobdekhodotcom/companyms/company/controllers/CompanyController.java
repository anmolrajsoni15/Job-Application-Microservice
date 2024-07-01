package com.jobdekhodotcom.companyms.company.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobdekhodotcom.companyms.company.entity.Company;
import com.jobdekhodotcom.companyms.company.services.CompanyService;


@RestController
@RequestMapping("/company")
public class CompanyController {

  @Autowired
  private CompanyService companyService;

  public CompanyController(CompanyService companyService) {
    this.companyService = companyService;
  }

  @GetMapping
  public ResponseEntity<?> getAllCompanies() {
    List<Company> companies = companyService.getAllCompanies();
    return new ResponseEntity<>(companies, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Company> updateCompany(@RequestBody Company company, @PathVariable UUID id) {
    Company updatedCompany = companyService.updateCompany(company, id);
    if (updatedCompany != null) {
      return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<?> createCompany(@RequestBody Company company) {
    try {
      companyService.createCompany(company);
      return new ResponseEntity<>("Company Created Successfully", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteCompanyById(@PathVariable UUID id) {
    if (companyService.deleteCompanyById(id)) {
      return new ResponseEntity<>("Company Deleted Successfully", HttpStatus.OK);
    } else {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Company> getCompanyById(@PathVariable UUID id) {
    Company company = companyService.getCompanyById(id);
    if (company != null) {
      return new ResponseEntity<>(company, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }
  
}
