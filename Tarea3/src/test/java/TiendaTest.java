import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TiendaTest {

    private Tienda tienda;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        tienda = new Tienda();
        cliente = tienda.agregarCliente("Ana", "ana@mail.com");
    }

    @Test
    public void givenNewCliente_whenAdded_thenClienteIsStored() {
        Cliente nuevo = tienda.agregarCliente("Juan", "juan@mail.com");
        assertEquals(nuevo, tienda.buscarClientePorId(nuevo.getId()));
    }

    @Test
    public void givenExistingClienteId_whenSearched_thenReturnsCliente() {
        Cliente resultado = tienda.buscarClientePorId(cliente.getId());
        assertNotNull(resultado);
        assertEquals("Ana", resultado.getNombre());
    }

    @Test
    public void givenNonExistentClienteId_whenSearched_thenReturnsNull() {
        Cliente resultado = tienda.buscarClientePorId("NO_EXISTE");
        assertNull(resultado);
    }

    @Test
    public void givenExistingCliente_whenUpdated_thenDataIsModified() {
        boolean actualizado = tienda.actualizarCliente(cliente.getId(), "Valentina", "vale@mail.com");
        assertTrue(actualizado);
        Cliente actualizadoCliente = tienda.buscarClientePorId(cliente.getId());
        assertEquals("Valentina", actualizadoCliente.getNombre());
        assertEquals("vale@mail.com", actualizadoCliente.getCorreo());
    }

    @Test
    public void givenNonExistentCliente_whenUpdated_thenReturnsFalse() {
        boolean actualizado = tienda.actualizarCliente("NO_EXISTE", "Nada", "nada@x.cl");
        assertFalse(actualizado);
    }

    @Test
    public void givenExistingCliente_whenDeleted_thenItIsRemoved() {
        boolean eliminado = tienda.eliminarCliente(cliente.getId());
        assertTrue(eliminado);
        assertNull(tienda.buscarClientePorId(cliente.getId()));
    }

    @Test
    public void givenNonExistentCliente_whenDeleted_thenReturnsFalse() {
        boolean eliminado = tienda.eliminarCliente("NO_EXISTE");
        assertFalse(eliminado);
    }

    @Test
    public void givenValidCliente_whenRegistrarCompra_thenCompraRegistrada() {
        Compra compra = tienda.registrarNuevaCompra(cliente.getId(), 1000);
        assertNotNull(compra);
        assertEquals(cliente.getId(), compra.getIdCliente());
        assertTrue(compra.getIdCompra().startsWith("COMP"));
        assertEquals(1000, compra.getMonto());
    }

    @Test
    public void givenInvalidCliente_whenRegistrarCompra_thenRetornaNull() {
        Compra compra = tienda.registrarNuevaCompra("NO_EXISTE", 500);
        assertNull(compra);
    }

    @Test
    public void givenClienteConCompra_whenMostrarCompras_thenNoFalla() {
        tienda.registrarNuevaCompra(cliente.getId(), 500);
        tienda.mostrarComprasDeCliente(cliente.getId());
    }

    @Test
    public void givenClienteSinCompras_whenMostrarCompras_thenNoFalla() {
        tienda.mostrarComprasDeCliente(cliente.getId());
    }

    @Test
    public void givenNoClientes_whenMostrarClientes_thenNoFalla() {
        Tienda tiendaVacia = new Tienda();
        tiendaVacia.mostrarClientes();
    }

    @Test
    public void givenClientesExistentes_whenMostrarClientes_thenNoFalla() {
        tienda.mostrarClientes();
    }

    @Test
    public void givenValidData_whenAgregarCliente_thenClienteRegistrado() {
        Cliente nuevo = tienda.agregarCliente("Luis", "luis@mail.com");
        assertNotNull(nuevo);
        assertTrue(nuevo.getId().startsWith("C"));
        assertEquals("Luis", nuevo.getNombre());
    }

    @Test
    public void givenInvalidCorreo_whenAgregarCliente_thenThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            tienda.agregarCliente("Luis", "correo");
        });
    }
}

