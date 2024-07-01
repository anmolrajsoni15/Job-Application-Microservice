package com.jobdekhodotcom.jobms.job.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobdekhodotcom.jobms.job.dto.JobDTO;
import com.jobdekhodotcom.jobms.job.entity.Job;
import com.jobdekhodotcom.jobms.job.services.JobService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/job")
public class JobController {

  @Autowired
  private JobService jobService;

  public JobController(JobService jobService) {
    this.jobService = jobService;
  }

  @GetMapping
  public ResponseEntity<List<JobDTO>> getAllJobs() {
    List<JobDTO> jobs = jobService.getAllJobs();
    return new ResponseEntity<>(jobs, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<?> createJob(@RequestBody Job job) {
    try {
      jobService.createJob(job);
      return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<JobDTO> getJobById(@PathVariable UUID id) {
    JobDTO job = jobService.getJobById(id);
    if (job != null) {
      return new ResponseEntity<>(job, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteJobById(@PathVariable UUID id) {
    try {
      jobService.deleteJobById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateJob(@PathVariable UUID id, @RequestBody Job updatedJob) {
    if (jobService.updateJob(id, updatedJob)) {
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
