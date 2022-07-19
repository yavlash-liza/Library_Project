package com.yavlash.library.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class OrderDto {
    Long id;
    LocalDate creationDate;
    LocalDate expirationDate;
    BigDecimal finalSum;
    List<BookCopyListDto> books;
    UserListDto reader;
}