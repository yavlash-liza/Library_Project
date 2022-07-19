package com.yavlash.library.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class OrderSaveDto {
    private Long id;
    private BigDecimal finalSum;
    private LocalDate expirationDate;
    private Long userId;
    private List<Long> bookCopiesId;
}