package com.example.dataaccess.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
public class Account extends ArchivableEntity {

    @Column(unique = true, updatable = false, nullable = false)
    private String login;

    @Column(unique = true, nullable = false)
    private String email;


}
