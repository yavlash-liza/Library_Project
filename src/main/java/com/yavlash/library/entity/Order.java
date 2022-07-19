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
public class Order extends BaseEntity<Long> {
    private LocalDate creationDate;
    private LocalDate expirationDate;
    private BigDecimal finalSum;
    private User user;

    @Builder
    public Order(Long id, BigDecimal finalSum, LocalDate creationDate, LocalDate expirationDate, User user) {
        setId(id);
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.finalSum = finalSum;
        this.user = user;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Order aThat = (Order) object;

        if (!super.equals(object)) {
            return false;
        }

        if (this.getCreationDate() == null) {
            if (aThat.getCreationDate() != null) {
                return false;
            }
        } else if (!this.getCreationDate().equals(aThat.getCreationDate())) {
            return false;
        }

        if (getExpirationDate() == null) {
            if (aThat.getExpirationDate() != null) {
                return false;
            }
        } else if (!getExpirationDate().equals(aThat.getExpirationDate())) {
            return false;
        }

        if (getFinalSum() == null) {
            return aThat.getFinalSum() == null;
        } else return getFinalSum().equals(aThat.getFinalSum());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (this.getCreationDate() == null ? 0 : this.getCreationDate().hashCode());
        result = prime * result + (getExpirationDate() == null ? 0 : getExpirationDate().hashCode());
        result = prime * result + (getFinalSum() == null ? 0 : getFinalSum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append("{")
                .append("id=").append(getId())
                .append(", finalSum=").append(getFinalSum())
                .append(", creationDate=").append(this.getCreationDate())
                .append(", expirationDate=").append(getExpirationDate())
                .append("}")
                .toString();
    }
}