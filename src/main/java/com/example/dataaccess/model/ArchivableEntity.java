package com.example.dataaccess.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@MappedSuperclass
public abstract class ArchivableEntity extends AbstractEntity {

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean archival;
}
