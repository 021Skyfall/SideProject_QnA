package IO.SideQnA.order.controller;

import IO.SideQnA.order.dto.OrderPatchDto;
import IO.SideQnA.order.dto.OrderPostDto;
import IO.SideQnA.order.entity.Order;
import IO.SideQnA.order.mapper.OrderMapper;
import IO.SideQnA.order.service.OrderService;
import IO.SideQnA.dto.MultiResponseDto;
import IO.SideQnA.dto.SingleResponseDto;
import IO.SideQnA.utils.UriCreator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
@Validated
@Slf4j
public class OrderController {
    private final static String ORDER_DEFAULT_URL = "/orders";
    private final OrderService service;
    private final OrderMapper mapper;

    @PostMapping
    public ResponseEntity postOrder(@Validated @RequestBody OrderPostDto orderPostDto) {
        Order order = service.createOrder(mapper.orderPostDtoToOrder(orderPostDto));

        URI location = UriCreator.createUri(ORDER_DEFAULT_URL, order.getOrderId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{order-id}")
    public ResponseEntity patchOrder(@PathVariable("order-id") @Positive long orderId,
                                     @Validated @RequestBody OrderPatchDto orderPatchDto) {
        orderPatchDto.setOrderId(orderId);
        Order order = service.updateOrder(mapper.orderPatchDtoToOrder(orderPatchDto));

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.orderToOrderResponseDto(order)),
                HttpStatus.OK);
    }

    @GetMapping("/{order-id}")
    public ResponseEntity getOrder(@PathVariable("order-id") @Positive long orderId) {
        Order order = service.findOrder(orderId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.orderToOrderResponseDto(order)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getOrders(@RequestParam @Positive int page,
                                    @RequestParam @Positive int size) {
        Page<Order> orderPage = service.findOrders(page -1, size);
        List<Order> orders = orderPage.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.ordersToOrderResponseDtos(orders),orderPage),
                HttpStatus.OK);
    }

    @DeleteMapping("/{order-id}")
    public ResponseEntity deleteOrder(@PathVariable("order-id") @Positive long orderId) {
        service.cancelOrder(orderId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
