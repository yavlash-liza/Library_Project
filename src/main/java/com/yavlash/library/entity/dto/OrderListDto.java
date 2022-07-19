package com.yavlash.library.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class OrderListDto {
    Long id;
    BigDecimal finalSum;
    LocalDate expirationDate;
    LocalDate creationDate;
}