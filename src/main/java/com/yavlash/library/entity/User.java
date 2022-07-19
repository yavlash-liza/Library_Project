package com.yavlash.library.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity<Long> {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String password;
    private String passportNumber;
    private String address;
    private String userStatus;
    private Role role;

    @Builder
    public User(Long id, String firstName, String lastName, String email, String address,
                String password, String passportNumber, LocalDate birthDate, Role role, String userStatus) {
        setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.password = password;
        this.passportNumber = passportNumber;
        this.birthDate = birthDate;
        this.role = role;
        this.userStatus = userStatus;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        User aThat = (User) object;

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
            if (aThat.getLastName() != null) {
                return false;
            }
        } else if (!getLastName().equals(aThat.getLastName())) {
            return false;
        }

        if (getEmail() == null) {
            if (aThat.getEmail() != null) {
                return false;
            }
        } else if (!getEmail().equals(aThat.getEmail())) {
            return false;
        }

        if (getAddress() == null) {
            if (aThat.getAddress() != null) {
                return false;
            }
        } else if (!getAddress().equals(aThat.getAddress())) {
            return false;
        }

        if (getUserStatus() == null) {
            if (aThat.getUserStatus() != null) {
                return false;
            }
        } else if (!getUserStatus().equals(aThat.getUserStatus())) {
            return false;
        }

        if (getPassword() == null) {
            if (aThat.getPassword() != null) {
                return false;
            }
        } else if (!getPassword().equals(aThat.getPassword())) {
            return false;
        }

        if (getPassportNumber() == null) {
            if (aThat.getPassportNumber() != null) {
                return false;
            }
        } else if (!getPassportNumber().equals(aThat.getPassportNumber())) {
            return false;
        }

        if (getRole() == null) {
            if (aThat.getRole() != null) {
                return false;
            }
        } else if (!getRole().equals(aThat.getRole())) {
            return false;
        }

        if (getBirthDate() == null) {
            return aThat.getBirthDate() == null;
        } else return getBirthDate().equals(aThat.getBirthDate());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (getFirstName() == null ? 0 : getFirstName().hashCode());
        result = prime * result + (getLastName() == null ? 0 : getLastName().hashCode());
        result = prime * result + (getBirthDate() == null ? 0 : getBirthDate().hashCode());
        result = prime * result + (getEmail() == null ? 0 : getEmail().hashCode());
        result = prime * result + (getAddress() == null ? 0 : getAddress().hashCode());
        result = prime * result + (getPassword() == null ? 0 : getPassword().hashCode());
        result = prime * result + (getPassportNumber() == null ? 0 : getPassportNumber().hashCode());
        result = prime * result + (getRole() == null ? 0 : getRole().hashCode());
        result = prime * result + (getUserStatus() == null ? 0 : getUserStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append("{")
                .append("id=").append(getId())
                .append(", firstName=").append(getFirstName())
                .append(", lastName=").append(getLastName())
                .append(", email=").append(getEmail())
                .append(", address=").append(getAddress())
                .append(", password=").append(getPassword())
                .append(", passportNumber=").append(getPassportNumber())
                .append(", birthDate=").append(getBirthDate())
                .append(", role=").append(getRole())
                .append(", userStatus=").append(getUserStatus())
                .append("}")
                .toString();
    }
}