package com.yavlash.library.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookDto {
    Long id;
    String title;
    int releaseYear;
    int pages;
    String bookPhotoPath;
    List<GenreDto> genres;
    List<AuthorDto> authors;
    List<BookCopyListDto> copies;
}
