package com.yavlash.library.entity.dto.converter;

import com.yavlash.library.entity.Book;
import com.yavlash.library.entity.BookCopy;
import com.yavlash.library.entity.dto.BookCopyDto;
import com.yavlash.library.entity.dto.BookCopyListDto;
import com.yavlash.library.entity.dto.BookCopySaveDto;

import java.util.List;
import java.util.stream.Collectors;

public class BookCopyConverter {
    public static BookCopyDto toDto(BookCopy bookCopy) {
        return BookCopyDto.builder()
            .id(bookCopy.getId())
            .title(bookCopy.getBook().getTitle())
            .copyStatus(bookCopy.getCopyStatus())
            .bookPhotoPath(bookCopy.getBook().getBookPhotoPath())
            .pricePerDay(bookCopy.getPricePerDay())
            .registerDate(bookCopy.getRegisterDate())
            .publishedYear(bookCopy.getPublishedYear())
            .build();
    }
    public static BookCopyListDto toListDto(BookCopy bookCopy) {
        return BookCopyListDto.builder()
                .id(bookCopy.getId())
                .pricePerDay(bookCopy.getPricePerDay())
                .title(bookCopy.getBook().getTitle())
                .build();
    }

    public static List<BookCopyListDto> toListDto(List<BookCopy> bookCopies) {
        return bookCopies.stream()
                .map(BookCopyConverter::toListDto)
                .collect(Collectors.toList());
    }

    public static BookCopy fromSaveDto(BookCopySaveDto bookCopySaveDto) {
        return BookCopy.builder()
            .pricePerDay(bookCopySaveDto.getPricePerDay())
            .publishedYear(bookCopySaveDto.getPublishedYear())
            .book(Book.builder().id(bookCopySaveDto.getBookId()).build())
            .build();
    }
}
