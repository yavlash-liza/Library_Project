package com.yavlash.library.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Role extends BaseEntity<Long> {
    private String roleName;

    @Builder
    public Role(Long id, String roleName) {
        setId(id);
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Role aThat = (Role) object;

        if (!super.equals(object)) {
            return false;
        }

        if (getRoleName() == null) {
            return aThat.getRoleName() == null;
        } else return getRoleName().equals(aThat.getRoleName());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (getRoleName() == null ? 0 : getRoleName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append("{")
                .append("id=").append(getId())
                .append(", roleName=").append(getRoleName())
                .append("}")
                .toString();
    }
}