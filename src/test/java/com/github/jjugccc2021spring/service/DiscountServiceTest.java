package com.github.jjugccc2021spring.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.jjugccc2021spring.bean.Purchase;
import com.github.jjugccc2021spring.core.LocalDateService;
import com.github.jjugccc2021spring.repository.PurchaseRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DiscountServiceTest {
  @Mock private LocalDateService localDateService;
  @Mock private PurchaseRepository purchaseRepository;
  @InjectMocks private DiscountService discountService;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 取引件数が5件の場合は200円割引() {
    String userId = "123";

    LocalDate toDate = LocalDate.of(2022, 6, 13);
    when(localDateService.now()).thenReturn(toDate);

    LocalDate fromDate = LocalDate.of(2022, 6, 6);

    List<Purchase> purcases =
        IntStream.range(1, 6).mapToObj(i -> new Purchase(toDate, 100 * i)).toList();
    when(purchaseRepository.findByPurchaseDateBetween(userId, fromDate, toDate))
        .thenReturn(purcases);

    int actual = discountService.discount(userId);

    assertEquals(200, actual);
    verify(localDateService).now();
    verify(purchaseRepository).findByPurchaseDateBetween(userId, fromDate, toDate);
  }
}
