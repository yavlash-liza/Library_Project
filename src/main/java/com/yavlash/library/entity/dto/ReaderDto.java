package com.yavlash.library.entity.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;
import java.util.List;

@Jacksonized
@Builder
@Value
public class ReaderDto {
    Long id;
    String firstName;
    String lastName;
    String email;
    LocalDate birthDate;
    String address;
    RoleDto role;
    List<OrderListDto> orders;
}