import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tienda {
    private List<Cliente> clientes = new ArrayList<>();
    private List<Compra> compras = new ArrayList<>();
    
    private int contadorClientes = 1;
    private int contadorCompras = 1;

    // Crear cliente con ID automático
    public Cliente agregarCliente(String nombre, String correo) {
        if (correo == null || !correo.contains("@")) {
            throw new IllegalArgumentException("Correo inválido");
        }
        String nuevoId = "C" + contadorClientes++;  // Ejemplo: C1, C2, ...
        Cliente nuevoCliente = new Cliente(nuevoId, nombre, correo);
        if (buscarClientePorId(nuevoId) != null) {
            throw new IllegalArgumentException("ID de cliente duplicado");
        }
        clientes.add(nuevoCliente);
        return nuevoCliente;
    }

    public void mostrarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("⚠️ No hay clientes registrados.");
        } else {
            System.out.println("📋 Lista de clientes:");
            for (Cliente c : clientes) {
                System.out.println("- ID: " + c.getId() + ", Nombre: " + c.getNombre() + ", Correo: " + c.getCorreo());
            }
        }
    }

    public Cliente buscarClientePorId(String id) {
        for (Cliente c : clientes) {
            if (c.getId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    public boolean actualizarCliente(String id, String nuevoNombre, String nuevoCorreo) {
        Cliente cliente = buscarClientePorId(id);
        if (cliente != null) {
            cliente.setNombre(nuevoNombre);
            cliente.setCorreo(nuevoCorreo);
            return true;
        }
        return false;
    }

    public boolean eliminarCliente(String id) {
        Cliente cliente = buscarClientePorId(id);
        if (cliente != null) {
            clientes.remove(cliente);
            return true;
        }
        return false;
    }
    
    public Compra registrarNuevaCompra(String idCliente, int monto) {
        Cliente cliente = buscarClientePorId(idCliente);

        if (cliente == null) {
            System.out.println("❌ No se encontró el cliente con ID: " + idCliente);
            return null;
        }

        String nuevoIdCompra = "COMP" + contadorCompras++;
        Compra nuevaCompra = new Compra(nuevoIdCompra, idCliente, monto, LocalDate.now());

        compras.add(nuevaCompra);

        int puntosGanados = nuevaCompra.calcularPuntosBase(cliente.getNivel());

        long comprasHoy = compras.stream()
            .filter(c -> c.getIdCliente().equals(cliente.getId()))
            .filter(c -> c.getFecha().equals(nuevaCompra.getFecha()))
            .count();

        if (comprasHoy == 3) {
            System.out.println("🎉 ¡Bonus por 3 compras en el mismo día! +10 puntos extra.");
            puntosGanados += 10;
        }
        
        cliente.setPuntos(cliente.getPuntos() + puntosGanados);
        actualizarNivel(cliente);

        System.out.println("✅ Compra registrada. Puntos ganados: " + puntosGanados + " para cliente " + cliente.getNombre());
        return nuevaCompra;
    }

    private void actualizarNivel(Cliente cliente) {
        int puntos = cliente.getPuntos();
        String nivel;

        if (puntos >= 3000) {
            nivel = "Platino";
        } else if (puntos >= 1500) {
            nivel = "Oro";
        } else if (puntos >= 500) {
            nivel = "Plata";
        } else {
            nivel = "Bronce";
        }

        cliente.setNivel(nivel);
    }

    public void mostrarComprasDeCliente(String idCliente) {
        boolean existe = false;
        for (Compra c : compras) {
            if (c.getIdCliente().equalsIgnoreCase(idCliente)) {
                System.out.println("Compra: ID " + c.getIdCompra() + " | Monto $" + c.getMonto() + " | Fecha " + c.getFecha());
                existe = true;
            }
        }
        if (!existe) {
            System.out.println("⚠️ No hay compras registradas para ese cliente.");
        }
    }

    public List<Compra> getCompras() {
        return compras;
    }
}

