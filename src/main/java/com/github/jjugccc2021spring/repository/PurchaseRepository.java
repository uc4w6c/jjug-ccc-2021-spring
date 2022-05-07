package com.github.jjugccc2021spring.repository;

import com.github.jjugccc2021spring.bean.Purchase;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PurchaseRepository {
  public List<Purchase> findByPurchaseDateBetween(
      String userId, LocalDate fromDate, LocalDate toDate) {
    return Collections.emptyList();
  }
}
