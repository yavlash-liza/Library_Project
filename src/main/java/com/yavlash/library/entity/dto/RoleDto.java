package com.yavlash.library.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {
    Long id;
    String roleName;
}
