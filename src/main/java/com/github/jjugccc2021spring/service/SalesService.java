package com.github.jjugccc2021spring.service;

import static java.lang.Math.max;

import com.github.jjugccc2021spring.bean.Sales;
import com.github.jjugccc2021spring.code.ShippingSize;

/** 売上サービス */
public class SalesService {
  /**
   * 販売利益計算
   *
   * @param price 商品代金
   * @param shippingSize 商品サイズ
   * @return 売上
   */
  public Sales profitCalculate(int price, ShippingSize shippingSize) {
    if (price < 1_000) {
      throw new IllegalArgumentException("priceには1,000円以上を設定してください。");
    }
    Sales sales = new Sales();
    sales.price = price;
    sales.profit = price;

    // 手数料計算
    int fee = feeCalculate(sales);

    // 配送料計算
    int deliveryFee = deliveryFeeCalculate(sales, shippingSize);

    // 配送料が300円以上なら手数料から100円割引
    if (deliveryFee >= 300) {
      fee = max(fee - 100, 100);
    }
    sales.fee = fee;
    sales.deliveryFee = deliveryFee;
    sales.profit = price - fee - deliveryFee;

    return sales;
  }

  private int feeCalculate(Sales sales) {
    int rate;
    if (sales.price < 5_000) {
      rate = 20;
    } else if (sales.price < 10_000) {
      rate = 10;
    } else {
      rate = 5;
    }
    return sales.price * rate / 100;
  }

  private int deliveryFeeCalculate(Sales sales, ShippingSize shippingSize) {
    return switch (shippingSize) {
      case SMALL -> 100;
      case MEDIUM -> 200;
      case LARGE -> 300;
    };
  }
}
