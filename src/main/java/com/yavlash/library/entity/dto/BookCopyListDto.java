package com.yavlash.library.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BookCopyListDto {
    Long id;
    String title;
    BigDecimal pricePerDay;
}
