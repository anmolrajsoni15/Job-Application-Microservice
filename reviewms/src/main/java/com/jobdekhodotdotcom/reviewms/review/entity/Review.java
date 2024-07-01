package com.jobdekhodotdotcom.reviewms.review.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Review {
  @Id
  @GeneratedValue(generator = "uuid")
  private UUID id;
  private String title;
  private String description;
  private double rating;
  private UUID companyId;
}
