package com.tup.programacion3.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true, exclude = "productos")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Categoria extends Base { // [cite: 1144]
    @EqualsAndHashCode.Include
    private String nombre;
    private String descripcion;
    @Builder.Default
    private Set<Producto> productos = new HashSet<>();

    public void addProducto(Producto p) {
        this.productos.add(p);
    }
}