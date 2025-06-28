import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tienda tienda = new Tienda();
        boolean flag = true;

        while (flag) {
            System.out.println("\n...............................................................\n");
            System.out.println("     --- Menú Principal ---");
            System.out.println(" 1. Gestión de Clientes");
            System.out.println(" 2. Gestión de Compras");
            System.out.println(" 3. Mostrar puntos y nivel de un cliente");
            System.out.println(" 4. Salir");
            System.out.print(" > Ingrese su selección: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: {
                    System.out.println("................................................................\n");
                    System.out.println("     --- Gestión de Clientes ---");
                    System.out.println(" 1. Crear nuevo cliente");
                    System.out.println(" 2. Mostrar todos los clientes");
                    System.out.println(" 3. Buscar cliente específico");
                    System.out.println(" 4. Actualizar cliente");
                    System.out.println(" 5. Eliminar cliente");
                    System.out.println(" 6. Volver al menú principal");
                    System.out.print(" > Ingrese su selección: ");
                    int opcionCliente = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcionCliente) {
                        case 1: {
                        	System.out.println("................................................................\n");
                            System.out.print("Ingrese nombre del cliente: ");
                            String nombre = scanner.nextLine();

                            System.out.print("Ingrese correo del cliente: ");
                            String correo = scanner.nextLine();

                            try {
                                Cliente nuevo = tienda.agregarClienteAutomatico(nombre, correo);
                                System.out.println("✅ Cliente agregado correctamente con ID: " + nuevo.getId());
                            } catch (IllegalArgumentException e) {
                                System.out.println("❌ Error: " + e.getMessage());
                            }
                            break;
                        }

                        case 2: {
                        	System.out.println("................................................................\n");
                            tienda.mostrarClientes();
                            break;
                        }

                        case 3: {
                        	System.out.println("................................................................\n");
                            System.out.print("Ingrese ID del cliente: ");
                            String idBuscar = scanner.nextLine();
                            Cliente cliente = tienda.buscarClientePorId(idBuscar);
                            if (cliente != null) {
                                System.out.println("📌 Cliente encontrado:");
                                System.out.println("ID: " + cliente.getId());
                                System.out.println("Nombre: " + cliente.getNombre());
                                System.out.println("Correo: " + cliente.getCorreo());
                            } else {
                                System.out.println("❌ Cliente no encontrado.");
                            }
                            break;
                        }

                        case 4: {
                        	System.out.println("................................................................\n");
                            System.out.print("Ingrese ID del cliente a actualizar: ");
                            String id = scanner.nextLine();
                            Cliente cliente = tienda.buscarClientePorId(id);
                            if (cliente != null) {
                                System.out.print("Nuevo nombre: ");
                                String nuevoNombre = scanner.nextLine();
                                System.out.print("Nuevo correo: ");
                                String nuevoCorreo = scanner.nextLine();
                                try {
                                    tienda.actualizarCliente(id, nuevoNombre, nuevoCorreo);
                                    System.out.println("✅ Cliente actualizado.");
                                } catch (IllegalArgumentException e) {
                                    System.out.println("❌ Correo inválido.");
                                }
                            } else {
                                System.out.println("❌ Cliente no encontrado.");
                            }
                            break;
                        }

                        case 5: {
                        	System.out.println("................................................................\n");
                            System.out.print("Ingrese ID del cliente a eliminar: ");
                            String id = scanner.nextLine();
                            boolean eliminado = tienda.eliminarCliente(id);
                            if (eliminado) {
                                System.out.println("✅ Cliente eliminado.");
                            } else {
                                System.out.println("❌ Cliente no encontrado.");
                            }
                            break;
                        }

                        case 6: {
                        	System.out.println("................................................................\n");
                            System.out.println("↩ Volviendo al menú principal...");
                            break;
                        }

                        default: {
                        	System.out.println("................................................................\n");
                            System.out.println("❌ Opción inválida.");
                            break;
                        }
                    }
                    break;
                }

                case 2: {
                    System.out.println("................................................................\n");
                    System.out.println("     --- Registro de Compra ---");
                    System.out.print("Ingrese ID del cliente: ");
                    String idCliente = scanner.nextLine();

                    System.out.print("Ingrese monto de la compra: ");
                    int monto = scanner.nextInt();
                    scanner.nextLine();

                    Compra compra = tienda.registrarCompraAutomatica(idCliente, monto);
                    if (compra != null) {
                        System.out.println("✅ Compra registrada con ID: " + compra.getIdCompra());
                    }
                    break;
                }

                case 3: {
                	System.out.println("................................................................\n");
                    System.out.print("Ingrese ID del cliente: ");
                    String id = scanner.nextLine();
                    Cliente cliente = tienda.buscarClientePorId(id);
                    if (cliente != null) {
                        cliente.verPuntosyNivel();
                        tienda.mostrarComprasDeCliente(id);
                    } else {
                        System.out.println("❌ Cliente no encontrado.");
                    }
                    break;
                }

                case 4: {
                	System.out.println("................................................................\n");
                    System.out.println("👋 ¡Hasta pronto!");
                    flag = false;
                    break;
                }

                default: {
                	System.out.println("................................................................\n");
                    System.out.println("❌ Opción inválida.");
                    break;
                }
            }
        }

        scanner.close();
    }
}

