package com.yavlash.library.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorDto {
    Long id;
    String firstName;
    String lastName;
}