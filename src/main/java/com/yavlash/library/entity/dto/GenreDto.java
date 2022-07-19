package com.yavlash.library.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenreDto {
    Long id;
    String genreName;
}