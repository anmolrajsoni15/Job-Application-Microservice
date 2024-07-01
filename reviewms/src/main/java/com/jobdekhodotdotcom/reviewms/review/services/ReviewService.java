package com.jobdekhodotdotcom.reviewms.review.services;

import java.util.List;
import java.util.UUID;

import com.jobdekhodotdotcom.reviewms.review.entity.Review;

public interface ReviewService {
  List<Review> getAllReviews(UUID companyId);
  boolean addReview(UUID companyId, Review review);
  Review getReview(UUID reviewId);
  boolean updateReview(UUID reviewId, Review review);
  boolean deleteReview(UUID reviewId);
}
