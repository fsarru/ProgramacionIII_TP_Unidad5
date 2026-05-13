package com.tup.programacion3.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetallePedido {
    private int cantidad;
    private Double subtotal;

    @EqualsAndHashCode.Include
    private Producto producto;
}