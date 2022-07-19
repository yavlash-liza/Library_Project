package com.yavlash.library.entity.dto.converter;

import com.yavlash.library.entity.Author;
import com.yavlash.library.entity.dto.AuthorDto;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorConverter {
    public static AuthorDto toDto(Author author) {
        return AuthorDto.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .build();
    }

    public static List<AuthorDto> toDto(List<Author> authors) {
        return authors.stream()
                .map(AuthorConverter::toDto)
                .collect(Collectors.toList());
    }

    public static Author fromDto(AuthorDto authorDto) {
        return Author.builder()
                .firstName(authorDto.getFirstName())
                .lastName(authorDto.getLastName())
                .build();
    }

    public static List<Author> fromDto(List<AuthorDto> authorsDto) {
        return authorsDto.stream()
                .map(AuthorConverter::fromDto)
                .collect(Collectors.toList());
    }
}