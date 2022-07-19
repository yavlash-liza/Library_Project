package com.yavlash.library.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class LibrarianDto {
    Long id;
    String firstName;
    String lastName;
    String email;
    LocalDate birthDate;
    String address;
    RoleDto role;
}