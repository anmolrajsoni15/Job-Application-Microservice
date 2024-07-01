package com.jobdekhodotcom.jobms.job.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Job {
  @Id
  @GeneratedValue(generator = "uuid")
  private UUID id;
  private String title;
  private String description;
  private String minSalary;
  private String maxSalary;
  private String location;
  private UUID companyId;
}
