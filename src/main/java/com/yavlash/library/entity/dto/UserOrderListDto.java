package com.yavlash.library.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserOrderListDto {
    private Long id;
    private String email;
}
