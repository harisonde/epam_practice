package com.epam;

import com.epam.model.LineItem;
import com.epam.model.Order;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FlatMapExample2 {

    public static void main(String[] args) {

        List<Order> orders = findAll();

        // sum the line items' total amount
        DoubleSummaryStatistics collect = orders.stream()
                .flatMap(order -> order.getLineItems().stream())
                .collect(Collectors.summarizingDouble(value -> value.getTotal().doubleValue()));


        BigDecimal sumOfLineItems = orders.stream()
                .flatMap(order -> order.getLineItems().stream())
                .map(LineItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
               // .reduce(BigDecimal.ZERO, (l1, l2) -> l1.add(l2));
               // .reduce(BigDecimal.ZERO, (l1, l2) -> l1.add(l2));

        BigDecimal orderTotalAmount = orders.stream()
                .map(Order::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Sum of line items is " + collect.getSum());
        System.out.println("Sum of line items using reduce operation is  " + sumOfLineItems);


        System.out.println(sumOfLineItems);                         // 3194.20
        System.out.println(orderTotalAmount);                             // 3194.20

        if (!orderTotalAmount.equals(sumOfLineItems)) {
            System.out.println("The sumOfOrder is not equals to sumOfLineItems!");
        }
    }


    // create dummy records
    private static List<Order> findAll() {

        LineItem item1 = new LineItem(1, "apple", 1, new BigDecimal("1.20"), new BigDecimal("1.20"));
        LineItem item2 = new LineItem(2, "orange", 2, new BigDecimal(".50"), new BigDecimal("1.00"));
        Order order1 = new Order(1, "A0000001", Arrays.asList(item1, item2), new BigDecimal("2.20"));

        LineItem item3 = new LineItem(3, "monitor BenQ", 5, new BigDecimal("99.00"), new BigDecimal("495.00"));
        LineItem item4 = new LineItem(4, "monitor LG", 10, new BigDecimal("120.00"), new BigDecimal("1200.00"));
        Order order2 = new Order(2, "A0000002", Arrays.asList(item3, item4), new BigDecimal("1695.00"));

        LineItem item5 = new LineItem(5, "One Plus 8T", 3, new BigDecimal("499.00"), new BigDecimal("1497.00"));
        Order order3 = new Order(3, "A0000003", Collections.singletonList(item5), new BigDecimal("1497.00"));

        return Arrays.asList(order1, order2, order3);
    }
}
