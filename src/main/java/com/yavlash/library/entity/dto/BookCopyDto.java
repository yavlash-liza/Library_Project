package com.yavlash.library.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class BookCopyDto {
    private Long id;
    private String title;
    private String copyStatus;
    private String bookPhotoPath;
    private BigDecimal pricePerDay;
    private LocalDate registerDate;
    private int publishedYear;
}
