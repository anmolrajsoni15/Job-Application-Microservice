package com.jobdekhodotdotcom.reviewms.review.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobdekhodotdotcom.reviewms.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, UUID>{
  List<Review> findByCompanyId(UUID companyId);
}
