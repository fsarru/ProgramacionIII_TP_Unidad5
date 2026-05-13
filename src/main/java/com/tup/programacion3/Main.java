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
        // --- 3.a Instanciar 2 Usuarios ---
        Usuario usuario1 = new Usuario(1L, "Franco", "Sarrú", "franco.dev@mail.com", "3411234567", Rol.ADMIN);
        Usuario usuario2 = new Usuario(2L, "Walter", "Sarrú", "walter.s@mail.com", "3419876543", Rol.USUARIO);

        // --- 3.c Instanciar 3 Categorías ---
        Categoria catHardware = new Categoria(1L, "Hardware PC", "Componentes y armado para desarrollo");
        Categoria catAsado = new Categoria(2L, "Carnicería", "Cortes tradicionales y embutidos");
        Categoria catIndumentaria = new Categoria(3L, "Deportes y Trekking", "Ropa");

        // --- 3.d Instanciar 10 Productos ---
        Set<Producto> inventario = new HashSet<>();

        Producto p1 = new Producto(1L, "Procesador AMD Ryzen", 250000.0, "Procesador AM5", 10, "cpu.jpg", true);
        Producto p2 = new Producto(2L, "Motherboard", 180000.0, "Placa base ASUS", 15, "mobo.jpg", true);
        Producto p3 = new Producto(3L, "Costillar de Cerdo", 15000.0, "Corte entero para asado", 5, "costillar.jpg", true);
        Producto p4 = new Producto(4L, "Chorizo", 6000.0, "Embutido fresco", 20, "chorizo.jpg", true);
        Producto p5 = new Producto(5L, "Yerba Mate", 3500.0, "Paquete 1kg", 50, "yerba.jpg", true);
        Producto p6 = new Producto(6L, "Termo Acero", 45000.0, "Mantiene agua caliente", 8, "termo.jpg", true);
        Producto p7 = new Producto(7L, "Remera", 80000.0, "Oficial titular", 12, "river.jpg", true);
        Producto p8 = new Producto(8L, "Zapatillas", 120000.0, "Suela reforzada montaña", 7, "zapas.jpg", true);
        Producto p9 = new Producto(9L, "Pantalón", 75000.0, "Denim clásico", 10, "jean.jpg", true);
        Producto p10 = new Producto(10L, "Carbón", 2000.0, "Bolsa 4kg", 30, "carbon.jpg", true);

        inventario.addAll(Set.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));

        // Asignar productos a categorías
        catHardware.addProducto(p1); catHardware.addProducto(p2);
        catAsado.addProducto(p3); catAsado.addProducto(p4); catAsado.addProducto(p10);
        catIndumentaria.addProducto(p7); catIndumentaria.addProducto(p8); catIndumentaria.addProducto(p9);

        // --- 3.b Instanciar 3 Pedidos (al menos 2 detalles por cada uno) ---
        Pedido pedido1 = new Pedido(1L, FormaPago.TRANSFERENCIA);
        pedido1.addDetallePedido(1, p1);
        pedido1.addDetallePedido(1, p2);
        usuario1.addPedido(pedido1);

        Pedido pedido2 = new Pedido(2L, FormaPago.TARJETA);
        pedido2.addDetallePedido(1, p7);
        pedido2.addDetallePedido(1, p9);
        usuario1.addPedido(pedido2);

        Pedido pedido3 = new Pedido(3L, FormaPago.EFECTIVO);
        pedido3.addDetallePedido(2, p3);
        pedido3.addDetallePedido(5, p4);
        pedido3.addDetallePedido(3, p10);
        usuario2.addPedido(pedido3);

        //  RESULTADOS POR CONSOLA (Punto 4)
        System.out.println("--- MOSTRANDO UN PRODUCTO ---");
        System.out.println(p1.toString());

        System.out.println("\n--- LISTADO DE PRODUCTOS CARGADOS ---");
        inventario.forEach(prod -> System.out.println(prod.toString()));

        System.out.println("\n--- PEDIDOS DEL USUARIO CON MÁS PEDIDOS ---");
        Usuario maxUser = (usuario1.getPedidos().size() > usuario2.getPedidos().size()) ? usuario1 : usuario2;
        System.out.println("El usuario con más pedidos es: " + maxUser.getNombre());
        for (Pedido ped : maxUser.getPedidos()) {
            System.out.println(ped.toString());
        }

        //  COMPARACIÓN (Punto 5)
        System.out.println("\n--- COMPARACIÓN DE PRODUCTO NUEVO (EQUALS Y UNICIDAD EN SET) ---");

        // CORRECCIÓN: El producto debe tener el MISMO ID y MISMO NOMBRE que p1 para ser "igual" según el método equals.
        Producto productoDuplicado = new Producto(1L, "Procesador AMD Ryzen", 280000.0, "Procesador Actualizado", 2, "cpu2.jpg", true);

        boolean encontrado = false;
        for (Producto prod : inventario) {
            if (prod.equals(productoDuplicado)) {
                System.out.println("¡Coincidencia encontrada lógicamente (equals)! -> " + prod.toString());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron coincidencias.");
        }

        // Demostrar cómo se comporta la colección (HashSet) con este duplicado lógico
        System.out.println("\nTamaño del inventario ANTES de intentar agregar el duplicado: " + inventario.size());
        boolean fueAgregado = inventario.add(productoDuplicado);

        System.out.println("¿Se agregó el duplicado al Set?: " + fueAgregado); // Debería dar false
        System.out.println("Tamaño del inventario DESPUÉS de intentar agregar: " + inventario.size());
    }
}