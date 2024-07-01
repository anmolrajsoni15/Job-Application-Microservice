package com.jobdekhodotcom.companyms.company.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewMessage {
  private UUID id;
  private String title;
  private String description;
  private double rating;
  private UUID companyId;
}
