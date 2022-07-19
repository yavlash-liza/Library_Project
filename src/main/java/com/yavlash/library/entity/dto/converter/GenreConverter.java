package com.yavlash.library.entity.dto.converter;

import com.yavlash.library.entity.Genre;
import com.yavlash.library.entity.dto.GenreDto;

import java.util.List;
import java.util.stream.Collectors;

public class GenreConverter {
    public static GenreDto toDto(Genre genre) {
        return GenreDto.builder()
                .id(genre.getId())
                .genreName(genre.getBookGenre())
                .build();
    }

    public static List<GenreDto> toDto(List<Genre> genres) {
        return genres.stream()
                .map(GenreConverter::toDto)
                .collect(Collectors.toList());
    }

    public static Genre fromDto(GenreDto genre) {
        return Genre.builder()
                .id(genre.getId())
                .bookGenre(genre.getGenreName())
                .build();
    }

    public static List<Genre> fromDto(List<GenreDto> genres) {
        return genres.stream()
                .map(GenreConverter::fromDto)
                .collect(Collectors.toList());
    }
}