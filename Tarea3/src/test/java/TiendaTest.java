import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TiendaTest {

    private Tienda tienda;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        tienda = new Tienda();
        cliente = new Cliente("001", "Ana", "ana@mail.com");
        tienda.agregarCliente(cliente);
    }

    @Test
    public void givenNewCliente_whenAdded_thenClienteIsStored() {
        Cliente nuevo = new Cliente("002", "Juan", "juan@mail.com");
        tienda.agregarCliente(nuevo);
        assertEquals(nuevo, tienda.buscarClientePorId("002"));
    }

    @Test
    public void givenDuplicatedClienteId_whenAdded_thenNotStoredTwice() {
        Cliente duplicado = new Cliente("001", "Otra Ana", "otra@correo.com");
        tienda.agregarCliente(duplicado);
        int count = 0;
        for (Cliente c : tienda.getClientes()) {
            if (c.getId().equals("001")) count++;
        }
        assertEquals(1, count);
    }

    @Test
    public void givenExistingClienteId_whenSearched_thenReturnsCliente() {
        Cliente resultado = tienda.buscarClientePorId("001");
        assertNotNull(resultado);
        assertEquals("Ana", resultado.getNombre());
    }

    @Test
    public void givenNonExistentClienteId_whenSearched_thenReturnsNull() {
        Cliente resultado = tienda.buscarClientePorId("999");
        assertNull(resultado);
    }

    @Test
    public void givenExistingCliente_whenUpdated_thenDataIsModified() {
        boolean actualizado = tienda.actualizarCliente("001", "Valentina", "vale@mail.com");
        assertTrue(actualizado);
        Cliente actualizadoCliente = tienda.buscarClientePorId("001");
        assertEquals("Valentina", actualizadoCliente.getNombre());
        assertEquals("vale@mail.com", actualizadoCliente.getCorreo());
    }

    @Test
    public void givenNonExistentCliente_whenUpdated_thenReturnsFalse() {
        boolean actualizado = tienda.actualizarCliente("999", "Nada", "nada@x.cl");
        assertFalse(actualizado);
    }

    @Test
    public void givenExistingCliente_whenDeleted_thenItIsRemoved() {
        boolean eliminado = tienda.eliminarCliente("001");
        assertTrue(eliminado);
        assertNull(tienda.buscarClientePorId("001"));
    }

    @Test
    public void givenNonExistentCliente_whenDeleted_thenReturnsFalse() {
        boolean eliminado = tienda.eliminarCliente("999");
        assertFalse(eliminado);
    }
}
