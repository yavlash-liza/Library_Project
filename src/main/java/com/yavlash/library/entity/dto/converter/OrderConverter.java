package com.yavlash.library.entity.dto.converter;

import com.yavlash.library.entity.BookCopy;
import com.yavlash.library.entity.Order;
import com.yavlash.library.entity.User;
import com.yavlash.library.entity.dto.OrderDto;
import com.yavlash.library.entity.dto.OrderLibrarianListDto;
import com.yavlash.library.entity.dto.OrderListDto;
import com.yavlash.library.entity.dto.OrderSaveDto;
import com.yavlash.library.entity.dto.UserListDto;

import java.util.List;
import java.util.stream.Collectors;

public class OrderConverter {
    public static OrderDto toOrderDto(Order order, List<BookCopy> bookCopies, UserListDto reader) {
        return OrderDto.builder()
                .id(order.getId())
                .creationDate(order.getCreationDate())
                .expirationDate(order.getExpirationDate())
                .finalSum(order.getFinalSum())
                .books(BookCopyConverter.toListDto(bookCopies))
                .reader(reader)
                .build();
    }

    public static List<OrderListDto> toOrderListDto(List<Order> orders) {
        return orders.stream()
                .map(OrderConverter::toOrderListDto)
                .collect(Collectors.toList());
    }

    public static OrderListDto toOrderListDto(Order order) {
        return OrderListDto.builder()
                .id(order.getId())
                .finalSum(order.getFinalSum())
                .expirationDate(order.getExpirationDate())
                .creationDate(order.getCreationDate())
                .build();
    }

    public static Order fromOrderSaveDto(OrderSaveDto orderSaveDto) {
        return Order.builder()
                .id(orderSaveDto.getId())
                .finalSum(orderSaveDto.getFinalSum())
                .expirationDate(orderSaveDto.getExpirationDate())
                .user(User.builder().id(orderSaveDto.getUserId()).build())
                .build();
    }

    public static List<OrderLibrarianListDto> toOrderLibrarianListDto(List<Order> orders) {
        return orders.stream()
            .map(OrderConverter::toOrderLibrarianListDto)
            .collect(Collectors.toList());
    }

    public static OrderLibrarianListDto toOrderLibrarianListDto(Order order) {
        return OrderLibrarianListDto.builder()
            .id(order.getId())
            .finalSum(order.getFinalSum())
            .expirationDate(order.getExpirationDate())
            .creationDate(order.getCreationDate())
            .user(UserConverter.toUserOrderListDto(order.getUser()))
            .build();
    }
}