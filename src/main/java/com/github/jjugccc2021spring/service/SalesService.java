package com.github.jjugccc2021spring.service;

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
    feeCalculate(sales);

    // 配送料計算
    deliveryFeeCalculate(sales, shippingSize);

    return sales;
  }

  private void feeCalculate(Sales sales) {
    int rate;
    if (sales.price < 5_000) {
      rate = 20;
    } else if (sales.price < 10_000) {
      rate = 10;
    } else {
      rate = 5;
    }
    int fee = sales.price * rate / 100;
    sales.fee = fee;
    sales.profit = sales.profit - fee;
  }

  private void deliveryFeeCalculate(Sales sales, ShippingSize shippingSize) {
    int deliveryFee =
        switch (shippingSize) {
          case SMALL -> 100;
          case MEDIUM -> 200;
          case LARGE -> 300;
        };
    // 配送料が300円以上なら手数料から100円割引
    if (deliveryFee >= 300) {
      sales.fee -= 100;
      sales.profit += 100;
    }
    sales.deliveryFee = deliveryFee;
    sales.profit = sales.profit - deliveryFee;
  }
}
