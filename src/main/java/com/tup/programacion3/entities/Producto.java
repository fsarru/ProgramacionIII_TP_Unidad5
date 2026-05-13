package main.java.com.tup.programacion3.entities;

import java.util.Objects;

public class Producto {
    private Long id;
    private String nombre;
    private Double precio;
    private String descripcion;
    private int stock;
    private String imagen;
    private Boolean disponible;

    public Producto(Long id, String nombre, Double precio, String descripcion, int stock, String imagen, Boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.imagen = imagen;
        this.disponible = disponible;
    }

    public String getNombre() { return nombre; }
    public Double getPrecio() { return precio; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto producto = (Producto) o;
        // Para que el punto 5 del TP funcione, la identidad principal la marcamos por el nombre
        return Objects.equals(nombre, producto.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=$" + precio +
                ", stock=" + stock +
                '}';
    }
}