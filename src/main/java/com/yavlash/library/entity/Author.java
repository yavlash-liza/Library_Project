package com.yavlash.library.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Author extends BaseEntity<Long> {
    private String firstName;
    private String lastName;

    @Builder
    public Author(Long id, String firstName, String lastName) {
        setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Author aThat = (Author) object;

        if (!super.equals(object)) {
            return false;
        }

        if (getFirstName() == null) {
            if (aThat.getFirstName() != null) {
                return false;
            }
        } else if (!getFirstName().equals(aThat.getFirstName())) {
            return false;
        }

        if (getLastName() == null) {
            return aThat.getLastName() == null;
        } else return getLastName().equals(aThat.getLastName());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (getFirstName() == null ? 0 : getFirstName().hashCode());
        result = prime * result + (getLastName() == null ? 0 : getLastName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append("{")
                .append("id=").append(getId())
                .append(", firstName=").append(getFirstName())
                .append(", lastName=").append(getLastName())
                .append("}")
                .toString();
    }
}