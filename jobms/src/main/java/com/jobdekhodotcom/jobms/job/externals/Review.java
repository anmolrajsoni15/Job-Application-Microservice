package com.jobdekhodotcom.jobms.job.externals;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Review {
  private UUID id;
  private String title;
  private String description;
  private double rating;
}
