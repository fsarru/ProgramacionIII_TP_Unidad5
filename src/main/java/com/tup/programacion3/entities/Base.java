package com.tup.programacion3.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Base {
    protected Long id;

    @Builder.Default
    protected boolean eliminado = false;

    @Builder.Default
    protected LocalDateTime createdAt = LocalDateTime.now();
}