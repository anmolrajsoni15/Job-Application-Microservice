package com.jobdekhodotdotcom.reviewms.review.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.jobdekhodotdotcom.reviewms.review.dto.ReviewMessage;
import com.jobdekhodotdotcom.reviewms.review.entity.Review;

@Service
public class ReviewMessageProducer {
  private final RabbitTemplate rabbitTemplate;

  public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void sendMessage(Review review) {
    ReviewMessage reviewMessage = new ReviewMessage();
    reviewMessage.setId(review.getId());
    reviewMessage.setTitle(review.getTitle());
    reviewMessage.setDescription(review.getDescription());
    reviewMessage.setRating(review.getRating());
    reviewMessage.setCompanyId(review.getCompanyId());

    rabbitTemplate.convertAndSend("companyRatingQueue", reviewMessage);
  }
}
