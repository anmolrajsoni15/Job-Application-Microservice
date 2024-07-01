package com.jobdekhodotcom.jobms.job.mappers;

import java.util.List;

import com.jobdekhodotcom.jobms.job.dto.JobDTO;
import com.jobdekhodotcom.jobms.job.entity.Job;
import com.jobdekhodotcom.jobms.job.externals.Company;
import com.jobdekhodotcom.jobms.job.externals.Review;

public class JobMapper {
  public static JobDTO mapToJobDTO(Job job, Company company, List<Review> reviews) {
    JobDTO jobWithCompanyDTO = new JobDTO();
    jobWithCompanyDTO.setId(job.getId());
    jobWithCompanyDTO.setTitle(job.getTitle());
    jobWithCompanyDTO.setDescription(job.getDescription());
    jobWithCompanyDTO.setMinSalary(job.getMinSalary());
    jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
    jobWithCompanyDTO.setLocation(job.getLocation());
    jobWithCompanyDTO.setCompany(company);
    jobWithCompanyDTO.setReview(reviews);
    return jobWithCompanyDTO;
  }
}
