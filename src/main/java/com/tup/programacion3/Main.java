package com.tup.programacion3;

import com.tup.programacion3.dtos.UsuarioDTO;
import com.tup.programacion3.entities.Categoria;
import com.tup.programacion3.entities.Pedido;
import com.tup.programacion3.entities.Producto;
import com.tup.programacion3.entities.Usuario;
import com.tup.programacion3.enums.FormaPago;
import com.tup.programacion3.enums.Rol;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // --- 3.a Instanciar 2 Usuarios usando Builder ---
        Usuario usuario1 = Usuario.builder()
                .id(1L)
                .nombre("Franco")
                .apellido("Sarrú")
                .mail("franco.dev@mail.com")
                .celular("3411234567")
                .contraseña("secreta123")
                .rol(Rol.ADMIN)
                .build();

        Usuario usuario2 = Usuario.builder()
                .id(2L)
                .nombre("Walter")
                .apellido("Sarrú")
                .mail("walter.s@mail.com")
                .celular("3419876543")
                .contraseña("pass456")
                .rol(Rol.USUARIO)
                .build();

        // --- 3.c Instanciar 3 Categorías usando Builder ---
        Categoria catHardware = Categoria.builder().id(1L).nombre("Hardware PC").descripcion("Componentes").build();
        Categoria catAsado = Categoria.builder().id(2L).nombre("Carnicería").descripcion("Cortes").build();
        Categoria catIndumentaria = Categoria.builder().id(3L).nombre("Deportes").descripcion("Ropa").build();

        // --- 3.d Instanciar 10 Productos usando Builder ---
        Set<Producto> inventario = new HashSet<>();

        Producto p1 = Producto.builder().id(1L).nombre("Procesador AMD Ryzen").precio(250000.0).stock(10).disponible(true).build();
        Producto p2 = Producto.builder().id(2L).nombre("Motherboard").precio(180000.0).stock(15).disponible(true).build();
        Producto p3 = Producto.builder().id(3L).nombre("Costillar de Cerdo").precio(15000.0).stock(5).disponible(true).build();
        Producto p4 = Producto.builder().id(4L).nombre("Chorizo").precio(6000.0).stock(20).disponible(true).build();
        Producto p5 = Producto.builder().id(5L).nombre("Yerba Mate").precio(3500.0).stock(50).disponible(true).build();
        Producto p6 = Producto.builder().id(6L).nombre("Termo Acero").precio(45000.0).stock(8).disponible(true).build();
        Producto p7 = Producto.builder().id(7L).nombre("Remera").precio(80000.0).stock(12).disponible(true).build();
        Producto p8 = Producto.builder().id(8L).nombre("Zapatillas").precio(120000.0).stock(7).disponible(true).build();
        Producto p9 = Producto.builder().id(9L).nombre("Pantalón").precio(75000.0).stock(10).disponible(true).build();
        Producto p10 = Producto.builder().id(10L).nombre("Carbón").precio(2000.0).stock(30).disponible(true).build();

        inventario.addAll(Set.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));

        // --- 3.b Instanciar 3 Pedidos usando Builder ---
        Pedido pedido1 = Pedido.builder().id(1L).formaPago(FormaPago.TRANSFERENCIA).build();
        pedido1.addDetallePedido(1, p1);
        pedido1.addDetallePedido(1, p2);
        usuario1.addPedido(pedido1);

        Pedido pedido2 = Pedido.builder().id(2L).formaPago(FormaPago.TARJETA).build();
        pedido2.addDetallePedido(1, p7);
        pedido2.addDetallePedido(1, p9);
        usuario1.addPedido(pedido2);

        Pedido pedido3 = Pedido.builder().id(3L).formaPago(FormaPago.EFECTIVO).build();
        pedido3.addDetallePedido(2, p3);
        pedido3.addDetallePedido(5, p4);
        usuario2.addPedido(pedido3);

        System.out.println("--- MOSTRANDO UN PRODUCTO (Generado por Lombok) ---");
        System.out.println(p1);

        System.out.println("\n--- PRUEBA DEL RECORD DTO (Ocultando datos sensibles) ---");
        UsuarioDTO dto = new UsuarioDTO(
                usuario1.getId(),
                usuario1.getNombre(),
                usuario1.getApellido(),
                usuario1.getMail(),
                usuario1.getCelular(),
                usuario1.getPedidos()
        );
        System.out.println(dto);


        System.out.println("\n========= RESOLUCIÓN TP PROGRAMACIÓN FUNCIONAL =========");

        System.out.println("\n--- 1. Cálculo de total usando Streams en Pedido ---");
        System.out.println("El total calculado con Streams para el Pedido 3 es: $" + pedido3.getTotal());

        // 2. Mostrar por consola productos disponibles
        System.out.println("\n--- 2. Productos Disponibles ---");
        inventario.stream()
                .filter(Producto::getDisponible)
                .forEach(p -> System.out.println("- " + p.getNombre()));

        // 3. Mostrar por consola la cantidad de ítems que tiene un pedido
        System.out.println("\n--- 3. Cantidad total de ítems en un pedido ---");
        int cantidadTotalItems = pedido3.getDetalles().stream()
                .mapToInt(detalle -> detalle.getCantidad())
                .sum();
        System.out.println("El pedido 3 contiene un total de " + cantidadTotalItems + " ítems físicos.");

        // 4. Detectar productos que tengan menos de 5 como valor en stock
        System.out.println("\n--- 4. Detectar productos con stock menor a 5 ---");

        // (Agregamos un producto extra con stock bajo al inventario solo para que esta prueba muestre un resultado por pantalla)
        Producto productoPocoStock = Producto.builder().id(11L).nombre("Sal Gruesa").precio(1500.0).stock(2).disponible(true).build();
        inventario.add(productoPocoStock);

        inventario.stream()
                .filter(p -> p.getStock() < 5)
                .forEach(p -> System.out.println("¡Alerta de Stock! -> " + p.getNombre() + " (Quedan: " + p.getStock() + ")"));

    }
}