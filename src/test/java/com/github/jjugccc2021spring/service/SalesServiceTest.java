package com.github.jjugccc2021spring.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.github.jjugccc2021spring.bean.Sales;
import com.github.jjugccc2021spring.code.ShippingSize;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class SalesServiceTest {
  @ParameterizedTest
  @ValueSource(ints = {0, 999})
  void priceに1000円未満を指定しExceptionが発生する(int price) {
    assertThatThrownBy(
            () -> {
              new SalesService().profitCalculate(price, ShippingSize.SMALL);
            })
        .isInstanceOf(IllegalArgumentException.class);
  }

  @ParameterizedTest
  @MethodSource("rate20Provider")
  void 手数料レートが20(int price, int expectFee, int expectProfit) {
    Sales actual = new SalesService().profitCalculate(price, ShippingSize.SMALL);

    assertEquals(price, actual.price);
    assertEquals(expectFee, actual.fee);
    assertEquals(expectProfit, actual.profit);
  }

  static Stream<Arguments> rate20Provider() {
    return Stream.of(Arguments.of(1_000, 200, 700), Arguments.of(4_999, 999, 3_900));
  }

  @ParameterizedTest
  @MethodSource("rate10Provider")
  void 手数料レートが10(int price, int expectFee, int expectProfit) {
    Sales actual = new SalesService().profitCalculate(price, ShippingSize.SMALL);

    assertEquals(price, actual.price);
    assertEquals(expectFee, actual.fee);
    assertEquals(expectProfit, actual.profit);
  }

  static Stream<Arguments> rate10Provider() {
    return Stream.of(Arguments.of(5_000, 500, 4_400), Arguments.of(9_999, 999, 8_900));
  }

  @ParameterizedTest
  @MethodSource("rate5Provider")
  void 手数料レートが5(int price, int expectFee, int expectProfit) {
    Sales actual = new SalesService().profitCalculate(price, ShippingSize.SMALL);

    assertEquals(price, actual.price);
    assertEquals(expectFee, actual.fee);
    assertEquals(expectProfit, actual.profit);
  }

  static Stream<Arguments> rate5Provider() {
    return Stream.of(Arguments.of(10_000, 500, 9_400));
  }

  @Test
  void 配送料が100円() {
    Sales actual = new SalesService().profitCalculate(1_000, ShippingSize.SMALL);

    assertEquals(1_000, actual.price);
    assertEquals(200, actual.fee);
    assertEquals(100, actual.deliveryFee);
    assertEquals(700, actual.profit);
  }

  @Test
  void 配送料が200円() {
    Sales actual = new SalesService().profitCalculate(1_000, ShippingSize.MEDIUM);

    assertEquals(1_000, actual.price);
    assertEquals(200, actual.fee);
    assertEquals(200, actual.deliveryFee);
    assertEquals(600, actual.profit);
  }

  @Test
  void 配送料が300円() {
    Sales actual = new SalesService().profitCalculate(1_000, ShippingSize.LARGE);

    assertEquals(1_000, actual.price);
    assertEquals(100, actual.fee);
    assertEquals(300, actual.deliveryFee);
    assertEquals(600, actual.profit);
  }
}
