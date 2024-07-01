package com.jobdekhodotdotcom.reviewms.review.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobdekhodotdotcom.reviewms.review.entity.Review;
import com.jobdekhodotdotcom.reviewms.review.messaging.ReviewMessageProducer;
import com.jobdekhodotdotcom.reviewms.review.services.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {
  @Autowired
  private ReviewService reviewService;
  private ReviewMessageProducer reviewMessageProducer;

  public ReviewController(ReviewService reviewService, ReviewMessageProducer reviewMessageProducer) {
    this.reviewService = reviewService;
    this.reviewMessageProducer = reviewMessageProducer;
  }

  @GetMapping
  public ResponseEntity<?> getAllReviews(@RequestParam UUID companyId) {
    List<Review> reviews = reviewService.getAllReviews(companyId);
    return new ResponseEntity<>(reviews, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<?> addReview(@RequestParam UUID companyId, @RequestBody Review review) {
    try {
      boolean isReviewed = reviewService.addReview(companyId, review);
      if (!isReviewed) {
        return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
      }
      reviewMessageProducer.sendMessage(review);
      return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("{reviewId}")
  public ResponseEntity<Review> getReview(@PathVariable UUID reviewId) {
    Review review = reviewService.getReview(reviewId);
    return new ResponseEntity<>(review, HttpStatus.OK);
  }

  @PutMapping("{reviewId}")
  public ResponseEntity<?> updateReview(@PathVariable UUID reviewId, @RequestBody Review review) {
    try {
      boolean isUpdated = reviewService.updateReview(reviewId, review);
      if (!isUpdated) {
        return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("{reviewId}")
  public ResponseEntity<?> deleteReview(@PathVariable UUID reviewId) {
    try {
      boolean isDeleted = reviewService.deleteReview(reviewId);
      if (!isDeleted) {
        return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/averageRating")
  public Double getAverageRating(@RequestParam UUID companyId) {
    List<Review> reviews = reviewService.getAllReviews(companyId);
    return reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);
  }
}
