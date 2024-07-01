package com.jobdekhodotdotcom.reviewms.review.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jobdekhodotdotcom.reviewms.review.entity.Review;
import com.jobdekhodotdotcom.reviewms.review.repository.ReviewRepository;


@Service
public class ReviewServiceImpl implements ReviewService{
  private ReviewRepository reviewRepository;

  public ReviewServiceImpl(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }

  @Override
  public List<Review> getAllReviews(UUID companyId) {
    List<Review> reviews = reviewRepository.findByCompanyId(companyId);
    return reviews;
  }

  @Override
  public boolean addReview(UUID companyId, Review review) {
    if(companyId != null && review != null) {
      review.setCompanyId(companyId);
      reviewRepository.save(review);
      return true;
    }
    return false;
  }

  @Override
  public Review getReview(UUID reviewId) {
    return reviewRepository.findById(reviewId).orElse(null);
  }

  @Override
  public boolean updateReview(UUID reviewId, Review review) {
    Review existingReview = reviewRepository.findById(reviewId).orElse(null);
    if(existingReview == null) {
      throw new RuntimeException("Review not found");
    }
    existingReview.setTitle(review.getTitle());
    existingReview.setDescription(review.getDescription());
    existingReview.setRating(review.getRating());
    existingReview.setCompanyId(review.getCompanyId());
    reviewRepository.save(existingReview);
    return true;
  }

  @Override
  public boolean deleteReview(UUID reviewId) {
    Review existingReview = reviewRepository.findById(reviewId).orElse(null);

    if(existingReview != null){
      reviewRepository.delete(existingReview);
      return true;
    } else {
      return false;
    }
  }
}
