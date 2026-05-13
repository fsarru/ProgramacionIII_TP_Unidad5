package main.java.com.tup.programacion3;

import main.java.com.tup.programacion3.entities.Categoria;
import main.java.com.tup.programacion3.entities.Pedido;
import main.java.com.tup.programacion3.entities.Producto;
import main.java.com.tup.programacion3.entities.Usuario;
import main.java.com.tup.programacion3.enums.FormaPago;
import main.java.com.tup.programacion3.enums.Rol;

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

        // --- RESULTADOS POR CONSOLA (Punto 4) ---
        System.out.println("--- MOSTRANDO UN PRODUCTO (Generado por Lombok) ---");
        System.out.println(p1);

        System.out.println("\n--- LISTADO DE PRODUCTOS CARGADOS ---");
        inventario.forEach(System.out::println);

        System.out.println("\n--- PEDIDOS DEL USUARIO CON MÁS PEDIDOS ---");
        Usuario maxUser = (usuario1.getPedidos().size() > usuario2.getPedidos().size()) ? usuario1 : usuario2;
        System.out.println("El usuario con más pedidos es: " + maxUser.getNombre());
        maxUser.getPedidos().forEach(System.out::println);

        // --- COMPARACIÓN (Punto 5) ---
        System.out.println("\n--- COMPARACIÓN DE PRODUCTO NUEVO (EQUALS) ---");
        Producto productoDuplicado = Producto.builder().id(99L).nombre("Procesador AMD Ryzen").precio(280000.0).stock(2).disponible(true).build();

        inventario.forEach(prod -> {
            if (prod.equals(productoDuplicado)) {
                System.out.println("¡Coincidencia encontrada lógicamente (equals)! -> " + prod.getNombre());
            }
        });

        // --- PRUEBA DEL DTO (Punto 6) ---
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
        // Verás que en el print del DTO NO aparece ni el Rol ni la Contraseña.
    }