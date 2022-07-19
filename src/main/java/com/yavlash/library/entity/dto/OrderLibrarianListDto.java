package com.yavlash.library.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class OrderLibrarianListDto {
    Long id;
    BigDecimal finalSum;
    LocalDate expirationDate;
    LocalDate creationDate;
    UserOrderListDto user;
}
