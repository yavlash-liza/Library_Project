package com.yavlash.library.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BookCopy extends BaseEntity<Long> {

    private BigDecimal pricePerDay;
    private LocalDate registerDate;
    private int publishedYear;

    private String copyStatus;
    private Book book;

    @Builder
    public BookCopy(Long id, BigDecimal pricePerDay, LocalDate registerDate, int publishedYear, String copyStatus,
        Book book) {
        setId(id);
        this.pricePerDay = pricePerDay;
        this.registerDate = registerDate;
        this.publishedYear = publishedYear;
        this.copyStatus = copyStatus;
        this.book = book;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        BookCopy aThat = (BookCopy) object;

        if (!super.equals(object)) {
            return false;
        }

        if (getPricePerDay() == null) {
            if (aThat.getPricePerDay() != null) {
                return false;
            }
        } else if (!getPricePerDay().equals(aThat.getPricePerDay())) {
            return false;
        }

        if (getRegisterDate() == null) {
            if (aThat.getRegisterDate() != null) {
                return false;
            }
        } else if (!getRegisterDate().equals(aThat.getRegisterDate())) {
            return false;
        }

        if (getCopyStatus() == null) {
            if (aThat.getCopyStatus() != null) {
                return false;
            }
        } else if (!getCopyStatus().equals(aThat.getCopyStatus())) {
            return false;
        }

        return getPublishedYear() == aThat.getPublishedYear();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (getPricePerDay() == null ? 0 : getPricePerDay().hashCode());
        result = prime * result + (getRegisterDate() == null ? 0 : getRegisterDate().hashCode());
        result = prime * result + (getCopyStatus() == null ? 0 : getCopyStatus().hashCode());
        result = prime * result + getPublishedYear();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
            .append("{")
            .append("id=").append(getId())
            .append(", pricePerDay=").append(getPricePerDay())
            .append(", registerDate=").append(getRegisterDate())
            .append(", publishedYear=").append(getPublishedYear())
            .append(", copyStatus=").append(getCopyStatus())
            .append("}")
            .toString();
    }
}