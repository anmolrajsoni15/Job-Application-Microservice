package com.jobdekhodotcom.jobms.job.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jobdekhodotcom.jobms.job.externals.Company;

@FeignClient(name = "COMPANYMS", url="${companyms.url}")
public interface CompanyClient {
  @GetMapping("/company/{id}")
  Company getCompany(@PathVariable("id") UUID id);
}
