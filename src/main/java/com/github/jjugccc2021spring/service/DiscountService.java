package com.github.jjugccc2021spring.service;

import com.github.jjugccc2021spring.bean.Purchase;
import com.github.jjugccc2021spring.core.LocalDateService;
import com.github.jjugccc2021spring.repository.PurchaseRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DiscountService {
  private LocalDateService localDateService;
  private PurchaseRepository purchaseRepository;

  public DiscountService(LocalDateService localDateService, PurchaseRepository purchaseRepository) {
    this.localDateService = localDateService;
    this.purchaseRepository = purchaseRepository;
  }

  /**
   * 割引金額の計算
   *
   * @param userId
   * @return 割引金額
   */
  public int discount(String userId) {
    LocalDate toDate = localDateService.now();
    LocalDate fromDate = toDate.minusDays(7);
    List<Purchase> purchases =
        purchaseRepository.findByPurchaseDateBetween(userId, fromDate, toDate);

    if (purchases.size() >= 5) {
      return 200;
    }
    if (purchases.size() >= 3) {
      return 100;
    }
    return 0;
  }
}
