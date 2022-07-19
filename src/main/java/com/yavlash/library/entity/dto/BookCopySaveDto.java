package com.yavlash.library.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BookCopySaveDto {
    private Long id;
    private int publishedYear;
    private BigDecimal pricePerDay;
    private int count;
    private Long bookId;
}
