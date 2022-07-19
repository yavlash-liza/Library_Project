package com.yavlash.library.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserSaveDto {
    Long id;
    String firstName;
    String lastName;
    LocalDate birthDate;
    String email;
    String password;
    String passportNumber;
    String address;
    Long roleId;
}
