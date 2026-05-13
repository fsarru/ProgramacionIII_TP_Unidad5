package main.java.com.tup.programacion3.entities;

import com.tup.programacion3.enums.Estado;
import main.java.com.tup.programacion3.enums.FormaPago;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Pedido {
    private Long id;
    private LocalDate fecha;
    private Estado estado;
    private Double total;
    private FormaPago formaPago;
    private Set<DetallePedido> detalles;

    public Pedido(Long id, FormaPago formaPago) {
        this.id = id;
        this.fecha = LocalDate.now();
        this.estado = Estado.PENDIENTE;
        this.formaPago = formaPago;
        this.detalles = new HashSet<>();
        this.total = 0.0;
    }

    public void addDetallePedido(int cantidad, Producto producto) {
        DetallePedido detalle = new DetallePedido(cantidad, producto);
        detalles.add(detalle);
        calcularTotal();
    }

    public DetallePedido findeDetallePedidoByProducto(Producto producto) {
        for (DetallePedido dp : detalles) {
            if (dp.getProducto().equals(producto)) {
                return dp;
            }
        }
        return null;
    }

    public void deleteDetallePedidoByProducto(Producto producto) {
        detalles.removeIf(dp -> dp.getProducto().equals(producto));
        calcularTotal();
    }

    // Ya no implementa la interfaz Calculable, pero conserva su comportamiento
    public void calcularTotal() {
        this.total = detalles.stream().mapToDouble(DetallePedido::getSubtotal).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id) && Objects.equals(fecha, pedido.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha);
    }

    @Override
    public String toString() {
        return "Pedido{id=" + id + ", fecha=" + fecha + ", estado=" + estado + ", total=$" + total + ", detalles=" + detalles.size() + "}";
    }
}