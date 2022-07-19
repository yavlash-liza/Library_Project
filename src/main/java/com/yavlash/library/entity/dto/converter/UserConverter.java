package com.yavlash.library.entity.dto.converter;

import com.yavlash.library.entity.Order;
import com.yavlash.library.entity.Role;
import com.yavlash.library.entity.User;
import com.yavlash.library.entity.dto.LibrarianDto;
import com.yavlash.library.entity.dto.ReaderDto;
import com.yavlash.library.entity.dto.UserListDto;
import com.yavlash.library.entity.dto.UserOrderListDto;
import com.yavlash.library.entity.dto.UserSaveDto;

import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {
    public static UserListDto toListDto(User user) {
        return UserListDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .status(user.getUserStatus())
                .role(RoleConverter.toDto(user.getRole()))
                .build();
    }

    public static List<UserListDto> toListDto(List<User> users) {
        return users.stream()
                .map(UserConverter::toListDto)
                .collect(Collectors.toList());
    }

    public static User fromDto(UserSaveDto userSaveDto) {
        return User.builder()
                .firstName(userSaveDto.getFirstName())
                .lastName(userSaveDto.getLastName())
                .birthDate(userSaveDto.getBirthDate())
                .email(userSaveDto.getEmail())
                .password(userSaveDto.getPassword())
                .passportNumber(userSaveDto.getPassportNumber())
                .address(userSaveDto.getAddress())
                .role(Role.builder().id(userSaveDto.getRoleId()).build())
                .build();
    }

    public static UserOrderListDto toUserOrderListDto(User user) {
        return UserOrderListDto.builder()
            .id(user.getId())
            .email(user.getEmail())
            .build();
    }

    public static LibrarianDto toLibrarianDto(User user) {
        return LibrarianDto.builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .birthDate(user.getBirthDate())
            .email(user.getEmail())
            .address(user.getAddress())
            .role(RoleConverter.toDto(user.getRole()))
            .build();
    }

    public static ReaderDto toDto (User user, List<Order> orders) {
        return ReaderDto.builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .birthDate(user.getBirthDate())
            .address(user.getAddress())
            .role(RoleConverter.toDto(user.getRole()))
            .orders(OrderConverter.toOrderListDto(orders))
            .build();
    }
}