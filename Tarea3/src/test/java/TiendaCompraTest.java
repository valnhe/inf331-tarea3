import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TiendaCompraTest {

    private Tienda tienda;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        tienda = new Tienda();
        cliente = tienda.agregarCliente("Ana", "ana@mail.com");
    }

    @Test
    public void givenBronceCliente_whenCompra1000_thenAdds10Points() {
        tienda.registrarNuevaCompra(cliente.getId(), 1000);

        assertEquals(10, cliente.getPuntos());
        assertEquals("Bronce", cliente.getNivel());
    }

    @Test
    public void givenCliente_when3ComprasSameDay_thenBonusApplied() {
        tienda.registrarNuevaCompra(cliente.getId(), 500); // 5
        tienda.registrarNuevaCompra(cliente.getId(), 500); // 5
        tienda.registrarNuevaCompra(cliente.getId(), 500); // 5 + 10 bonus

        assertEquals(25, cliente.getPuntos());
    }

    @Test
    public void givenClienteWithManyPoints_whenCompra_thenLevelUp() {
        cliente.setPuntos(1490); // Casi Oro
        tienda.registrarNuevaCompra(cliente.getId(), 1000); // +10

        assertEquals("Oro", cliente.getNivel());
    }

    @Test
    public void givenInvalidClienteId_whenCompra_thenIgnored() {
        Compra compra = tienda.registrarNuevaCompra("NO_EXISTE", 1000);
        assertNull(compra);
        assertEquals(0, tienda.getCompras().size());
    }
}

