package com.jobdekhodotcom.jobms.job.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.core.ParameterizedTypeReference;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jobdekhodotcom.jobms.job.client.CompanyClient;
import com.jobdekhodotcom.jobms.job.client.ReviewClient;
import com.jobdekhodotcom.jobms.job.dto.JobDTO;
import com.jobdekhodotcom.jobms.job.entity.Job;
import com.jobdekhodotcom.jobms.job.externals.Company;
import com.jobdekhodotcom.jobms.job.externals.Review;
import com.jobdekhodotcom.jobms.job.mappers.JobMapper;
import com.jobdekhodotcom.jobms.job.repository.JobRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class JobServiceImpl implements JobService {
  @Autowired
  JobRepository jobRepository;

  @Autowired
  RestTemplate restTemplate;

  private CompanyClient companyClient;
  private ReviewClient reviewClient;

  public JobServiceImpl(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
    this.jobRepository = jobRepository;
    this.companyClient = companyClient;
    this.reviewClient = reviewClient;
  }

  @Override
  @CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
  @Retry(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
  @RateLimiter(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
  public List<JobDTO> getAllJobs() {
    List<Job> jobs = jobRepository.findAll();

    return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  public List<String> companyBreakerFallback(Exception e) {
    return List.of("Company service is down, please try again later");
  }

  private JobDTO convertToDto(Job job) {
    // String companyUrl = "http://COMPANYMS:8081/companies/" + job.getCompanyId();
    // String reviewUrl = "http://REVIEWMS:8083/reviews/?companyId=" + job.getCompanyId();
    Company company = companyClient.getCompany(job.getCompanyId());

    // ResponseEntity<List<Review>> response = restTemplate.exchange(reviewUrl, HttpMethod.GET, null,
    //     new ParameterizedTypeReference<List<Review>>() {
    //     });

    // List<Review> reviews = response.getBody();
    List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

    JobDTO jobDTO = JobMapper.mapToJobDTO(job, company, reviews);
    return jobDTO;
  }

  @Override
  public void createJob(Job job) {
    jobRepository.save(job);
  }

  @Override
  public JobDTO getJobById(UUID id) {
    Job job = jobRepository.findById(id).orElse(null);
    return convertToDto(job);
  }

  @Override
  public boolean deleteJobById(UUID id) {
    if (jobRepository.existsById(id)) {
      jobRepository.deleteById(id);
      return true;
    }
    return false;
  }

  @Override
  public boolean updateJob(UUID id, Job updatedJob) {
    Optional<Job> jobOptional = jobRepository.findById(id);
    if(jobOptional.isPresent()){
      Job job = jobOptional.get();
      job.setTitle(updatedJob.getTitle());
      job.setDescription(updatedJob.getDescription());
      job.setMinSalary(updatedJob.getMinSalary());
      job.setMaxSalary(updatedJob.getMaxSalary());
      job.setLocation(updatedJob.getLocation());
      jobRepository.save(job);
      return true;
    }
    return false;
  }
}
