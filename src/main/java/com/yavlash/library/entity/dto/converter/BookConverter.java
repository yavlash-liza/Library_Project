package com.yavlash.library.entity.dto.converter;

import com.yavlash.library.entity.Book;
import com.yavlash.library.entity.dto.BookDto;
import com.yavlash.library.entity.dto.BookListDto;
import com.yavlash.library.entity.dto.BookSaveDto;

import java.util.List;
import java.util.stream.Collectors;

public class BookConverter {
    public static BookDto toDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .releaseYear(book.getReleaseYear())
                .pages(book.getPages())
                .bookPhotoPath(book.getBookPhotoPath())
                .genres(GenreConverter.toDto(book.getGenres()))
                .authors(AuthorConverter.toDto(book.getAuthors()))
                .copies(BookCopyConverter.toListDto(book.getBookCopies()))
                .build();
    }

    public static BookListDto toListDto(Book book) {
        return BookListDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .bookPhotoPath(book.getBookPhotoPath())
                .genres(GenreConverter.toDto(book.getGenres()))
                .authors(AuthorConverter.toDto(book.getAuthors()))
                .build();
    }

    public static List<BookListDto> toListDto(List<Book> books) {
        return books.stream()
                .map(BookConverter::toListDto)
                .collect(Collectors.toList());
    }

    public static Book fromBookSaveDto(BookSaveDto  bookSaveDto) {
        return Book.builder()
                .id(bookSaveDto.getId())
                .title(bookSaveDto.getTitle())
                .releaseYear(bookSaveDto.getReleaseYear())
                .pages(bookSaveDto.getPages())
                .bookPhotoPath(bookSaveDto.getBookPhotoPath())
                .genres(GenreConverter.fromDto(bookSaveDto.getGenres()))
                .authors(AuthorConverter.fromDto(bookSaveDto.getAuthors()))
                .build();
    }
}