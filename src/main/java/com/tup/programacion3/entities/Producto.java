package com.tup.programacion3.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producto {
    private Long id;

    @EqualsAndHashCode.Include // Identidad por nombre, igual que en el TP5
    private String nombre;

    private Double precio;
    private String descripcion;
    private int stock;
    private String imagen;
    private Boolean disponible;
}