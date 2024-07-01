package com.jobdekhodotcom.companyms.company.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.jobdekhodotcom.companyms.company.dto.ReviewMessage;
import com.jobdekhodotcom.companyms.company.services.CompanyService;

@Service
public class ReviewMessageConsumer {
  private final CompanyService companyService;

  public ReviewMessageConsumer(CompanyService companyService) {
    this.companyService = companyService;
  }

  @RabbitListener(queues = "companyRatingQueue")
  public void consumeMessage(ReviewMessage reviewMessage) {
    companyService.updateCompanyRating(reviewMessage);
  }
}
