package IO.SideQnA.order.dto;

import IO.SideQnA.order.entity.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
public class OrderPatchDto {
    @Setter
    private long orderId;
    private Order.OrderStatus orderStatus;
}
