package com.tup.programacion3.entities;

import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "productos") // Excluimos la lista para evitar logs gigantes
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria {
    @EqualsAndHashCode.Include
    private Long id;

    @EqualsAndHashCode.Include
    private String nombre;

    private String descripcion;

    @Builder.Default
    private Set<Producto> productos = new HashSet<>();

    public void addProducto(Producto p) {
        this.productos.add(p);
    }
}