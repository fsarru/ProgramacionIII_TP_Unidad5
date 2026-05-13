package com.tup.programacion3.entities;

import main.java.com.tup.programacion3.enums.Rol;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"contraseña", "pedidos"}) // Protegemos la contraseña en los logs
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {
    private Long id;
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