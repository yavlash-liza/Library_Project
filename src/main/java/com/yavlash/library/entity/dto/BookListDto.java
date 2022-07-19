package com.yavlash.library.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookListDto {
    Long id;
    String title;
    String bookPhotoPath;
    List<GenreDto> genres;
    List<AuthorDto> authors;
}