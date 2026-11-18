package com.codifyai.distributed.common_lib.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static com.codifyai.distributed.common_lib.enums.ProjectPermission.*;

@RequiredArgsConstructor
@Getter
public enum ProjectRole {
    OWNER(EDIT, DELETE, VIEW, MANAGE_MEMBERS, VIEW_MEMBERS), EDITOR(Set.of(EDIT, VIEW, VIEW_MEMBERS)), VIEWER(Set.of(VIEW, VIEW_MEMBERS));

    private final Set<ProjectPermission> permissions;

    ProjectRole(ProjectPermission... permissions) {
        this.permissions = Set.of(permissions);
    }
}
