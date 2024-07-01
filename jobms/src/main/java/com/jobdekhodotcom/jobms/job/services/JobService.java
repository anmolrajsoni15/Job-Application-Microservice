package com.jobdekhodotcom.jobms.job.services;

import java.util.List;
import java.util.UUID;

import com.jobdekhodotcom.jobms.job.dto.JobDTO;
import com.jobdekhodotcom.jobms.job.entity.Job;

public interface JobService {
  List<JobDTO> getAllJobs();
  void createJob(Job job);
  JobDTO getJobById(UUID id);
  boolean deleteJobById(UUID id);
  boolean updateJob(UUID id, Job updatedJob);
}
