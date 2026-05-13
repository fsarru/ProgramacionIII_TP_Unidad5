package main.java.com.tup.programacion3.entities;

import main.java.com.tup.programacion3.enums.Rol;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Usuario {
    private Long id;
    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String contraseña;
    private Rol rol;
    private Set<Pedido> pedidos;

    public Usuario(Long id, String nombre, String apellido, String mail, String celular, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.celular = celular;
        this.rol = rol;
        this.pedidos = new HashSet<>();
    }

    public String getNombre() { return nombre; }
    public Set<Pedido> getPedidos() { return pedidos; }

    public void addPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(mail, usuario.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail);
    }

    @Override
    public String toString() {
        return "Usuario{id=" + id + ", nombre='" + nombre + " " + apellido + "', mail='" + mail + "', pedidosRealizados=" + pedidos.size() + "}";
    }
}