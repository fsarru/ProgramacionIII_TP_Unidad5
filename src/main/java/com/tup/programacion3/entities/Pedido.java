package main.java.com.tup.programacion3.entities;

import com.tup.programacion3.enums.Estado;
import com.tup.programacion3.enums.FormaPago;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "detalles")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {
    @EqualsAndHashCode.Include
    private Long id;

    @Builder.Default
    private LocalDate fecha = LocalDate.now();

    @Builder.Default
    private Estado estado = Estado.PENDIENTE;

    @Builder.Default
    private Double total = 0.0;

    private FormaPago formaPago;

    @Builder.Default
    private Set<DetallePedido> detalles = new HashSet<>();

    // Mantenemos la lógica de negocio que no puede automatizar Lombok
    public void addDetallePedido(int cantidad, Producto producto) {
        DetallePedido detalle = DetallePedido.builder()
                .cantidad(cantidad)
                .producto(producto)
                .subtotal(cantidad * producto.getPrecio())
                .build();
        detalles.add(detalle);
        calcularTotal();
    }

    public void deleteDetallePedidoByProducto(Producto producto) {
        detalles.removeIf(dp -> dp.getProducto().equals(producto));
        calcularTotal();
    }

    public void calcularTotal() {
        this.total = detalles.stream().mapToDouble(DetallePedido::getSubtotal).sum();
    }
}