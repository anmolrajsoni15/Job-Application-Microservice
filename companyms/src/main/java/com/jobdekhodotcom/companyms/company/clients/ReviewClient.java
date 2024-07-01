package com.jobdekhodotcom.companyms.company.clients;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "REVIEWMS", url="${reviewms.url}")
public interface ReviewClient {
  
  @GetMapping("/review/averageRating")
  public double getAverageRatingForCompany(@RequestParam UUID companyId);
}
