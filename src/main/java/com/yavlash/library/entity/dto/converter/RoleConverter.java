package com.yavlash.library.entity.dto.converter;

import com.yavlash.library.entity.Role;
import com.yavlash.library.entity.dto.RoleDto;

public class RoleConverter {
    public static RoleDto toDto(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .build();
    }
}
