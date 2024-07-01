package com.jobdekhodotcom.jobms.job.client;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jobdekhodotcom.jobms.job.externals.Review;

@FeignClient(name = "REVIEWMS", url="${reviewms.url}")
public interface ReviewClient {
  @GetMapping("/review")
  List<Review> getReviews(@RequestParam("companyId") UUID companyId);
}
