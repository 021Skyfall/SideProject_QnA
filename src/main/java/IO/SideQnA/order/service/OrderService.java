package IO.SideQnA.order.service;

import IO.SideQnA.coffee.service.CoffeeService;
import IO.SideQnA.exception.BusinessLogicException;
import IO.SideQnA.exception.ExceptionCode;
import IO.SideQnA.member.entity.Member;
import IO.SideQnA.member.service.MemberService;
import IO.SideQnA.order.entity.Order;
import IO.SideQnA.order.entity.OrderCoffee;
import IO.SideQnA.order.repository.OrderRepository;
import IO.SideQnA.member.entity.Stamp;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class OrderService {
    private final MemberService memberService;
    private final CoffeeService coffeeService;
    private final OrderRepository repository;

    public Order createOrder(Order order) {
        verifyOrder(order);

        Order savedOrder = savedOrder(order);

        updateStamp(savedOrder);

        return savedOrder;
    }

    public Order updateOrder(Order order) {
        Order findOrder = findVerifiedOrder(order.getOrderId());

        Optional.ofNullable(order.getOrderStatus())
                .ifPresent(findOrder::setOrderStatus);

        return repository.save(findOrder);
    }

    public Order findOrder(long orderId) {
        return findVerifiedOrder(orderId);
    }

    public Page<Order> findOrders(int page, int size) {
        return repository.findAll(PageRequest.of(page, size,
                Sort.by("orderId").descending()));
    }

    public void cancelOrder(long orderId) {
        Order findOrder = findVerifiedOrder(orderId);
        int step = findOrder.getOrderStatus().getStepNumber();

        if (step >= 2) {
            throw new BusinessLogicException(ExceptionCode.CANNOT_CHANGE_ORDER);
        }
        findOrder.setOrderStatus(Order.OrderStatus.ORDER_CANCEL);
        repository.save(findOrder);
    }

    private Order findVerifiedOrder(long orderId) {
        Optional<Order> optionalOrder = repository.findById(orderId);
        Order findOrder =
                optionalOrder.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.ORDER_NOT_FOUND));
        return findOrder;
    }

    private void verifyOrder(Order order) {
        memberService.findVerifiedMember(order.getMember().getMemberId());
        order.getOrderCoffees()
                .forEach(e -> coffeeService.findVerifiedCoffee(e.getCoffee().getCoffeeId()));
    }

    private void updateStamp(Order order) {
        Member member = memberService.findMember(order.getMember().getMemberId());
        int stampCount = calculateStampCount(order);

        Stamp stamp = member.getStamp();
        stamp.setStampCount(stamp.getStampCount() + stampCount);

        memberService.updateMember(member);
    }

    private int calculateStampCount(Order order) {
        return order.getOrderCoffees().stream()
                .map(OrderCoffee::getQuantity)
                .mapToInt(e -> e)
                .sum();
    }

    private Order savedOrder(Order order) {
        return repository.save(order);
    }
}
