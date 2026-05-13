package main.java.com.tup.programacion3.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Categoria {
    private Long id;
    private String nombre;
    private String descripcion;
    private Set<Producto> productos;

    public Categoria(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productos = new HashSet<>();
    }

    public void addProducto(Producto p) {
        this.productos.add(p);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria)) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id) && Objects.equals(nombre, categoria.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @Override
    public String toString() {
        return "Categoria{id=" + id + ", nombre='" + nombre + "', cantidadProductos=" + productos.size() + "}";
    }
}