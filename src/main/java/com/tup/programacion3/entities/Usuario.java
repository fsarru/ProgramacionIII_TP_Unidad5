package com.tup.programacion3.entities;

import com.tup.programacion3.enums.Rol;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true, exclude = {"contraseña", "pedidos"})
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Usuario extends Base {
    private String nombre;
    private String apellido;
    @EqualsAndHashCode.Include
    private String mail;
    private String celular;
    private String contraseña;
    private Rol rol;

    @Builder.Default
    private Set<Pedido> pedidos = new HashSet<>();

    public void addPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }
}