package com.jobdekhodotcom.companyms.company.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Company {

  @Id
  @GeneratedValue(generator = "uuid")
  private UUID id;
  private String name;
  private String description;
  private double rating;
}
