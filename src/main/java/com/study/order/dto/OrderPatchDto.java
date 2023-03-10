package com.study.order.dto;

import com.study.order.entity.Order;
import lombok.Getter;

// OrderPatchDto 추가 됨
@Getter
public class OrderPatchDto {
    private long orderId;
    private Order.OrderStatus orderStatus;

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
