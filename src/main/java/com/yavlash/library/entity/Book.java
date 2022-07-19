package com.yavlash.library.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Book extends BaseEntity<Long> {
    private String title;
    private int releaseYear;
    private int pages;
    private String bookPhotoPath;
    private List<Genre> genres;
    private List<BookCopy> bookCopies;
    private List<Author> authors;

    @Builder
    public Book(Long id, String title, int releaseYear, int pages, String bookPhotoPath, List<Genre> genres, List<BookCopy> bookCopies, List<Author> authors) {
        setId(id);
        this.title = title;
        this.releaseYear = releaseYear;
        this.pages = pages;
        this.bookPhotoPath = bookPhotoPath;
        this.genres = genres;
        this.bookCopies = bookCopies;
        this.authors = authors;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Book aThat = (Book) object;

        if (!super.equals(object)) {
            return false;
        }

        if (getTitle() == null) {
            if (aThat.getTitle() != null) {
                return false;
            }
        } else if (!getTitle().equals(aThat.getTitle())) {
            return false;
        }

        if (getReleaseYear() != aThat.getReleaseYear()) {
            return false;
        }

        if (getPages() != aThat.getPages()) {
            return false;
        }

        if (getBookPhotoPath() == null) {
            return aThat.getBookPhotoPath() == null;
        } else return getBookPhotoPath().equals(aThat.getBookPhotoPath());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (getTitle() == null ? 0 : getTitle().hashCode());
        result = prime * result + getReleaseYear();
        result = prime * result + getPages();
        result = prime * result + (getBookPhotoPath() == null ? 0 : getBookPhotoPath().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append("{")
                .append("id=").append(getId())
                .append(", title=").append(getTitle())
                .append(", releaseYear=").append(getReleaseYear())
                .append(", pages=").append(getPages())
                .append(", bookPhotoPath=").append(getBookPhotoPath())
                .append("}")
                .toString();
    }
}