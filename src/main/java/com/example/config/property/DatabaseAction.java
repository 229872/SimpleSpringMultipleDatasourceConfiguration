package com.example.config.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DatabaseAction {
    NONE("none"), CREATE("create"), DROP_AND_CREATE("drop-and-create"),
    DROP("drop");

    private final String actionName;
}
