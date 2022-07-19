package com.yavlash.library.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Genre extends BaseEntity<Long> {
    private String bookGenre;

    @Builder
    public Genre(Long id, String bookGenre) {
        setId(id);
        this.bookGenre = bookGenre;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Genre aThat = (Genre) object;

        if (!super.equals(object)) {
            return false;
        }

        if (getBookGenre() == null) {
            return aThat.getBookGenre() == null;
        } else return getBookGenre().equals(aThat.getBookGenre());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (getBookGenre() == null ? 0 : getBookGenre().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append("{")
                .append("id=").append(getId())
                .append(", bookGenre=").append(getBookGenre())
                .append("}")
                .toString();
    }
}