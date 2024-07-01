package com.jobdekhodotcom.jobms.job.dto;

import java.util.List;
import java.util.UUID;
import com.jobdekhodotcom.jobms.job.externals.Company;
import com.jobdekhodotcom.jobms.job.externals.Review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobDTO {
  private UUID id;
  private String title;
  private String description;
  private String minSalary;
  private String maxSalary;
  private String location;
  private Company company;
  private List<Review> review;
}
