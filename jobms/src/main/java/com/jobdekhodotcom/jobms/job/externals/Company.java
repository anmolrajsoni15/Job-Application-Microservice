package com.jobdekhodotcom.jobms.job.externals;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Company {
  private UUID id;
  private String name;
  private String description;
}
