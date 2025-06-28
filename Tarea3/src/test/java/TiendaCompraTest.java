import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TiendaCompraTest {

    private Tienda tienda;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        tienda = new Tienda();
        cliente = new Cliente("123", "Ana", "ana@mail.com");
        tienda.agregarCliente(cliente);
    }

    @Test
    public void givenBronceCliente_whenCompra1000_thenAdds10Points() {
        Compra compra = new Compra("C1", "123", 1000, LocalDate.now());
        tienda.registrarCompra(compra);

        assertEquals(10, cliente.getPuntos());
        assertEquals("Bronce", cliente.getNivel());
    }

    @Test
    public void givenCliente_when3ComprasSameDay_thenBonusApplied() {
        LocalDate hoy = LocalDate.now();

        tienda.registrarCompra(new Compra("C1", "123", 500, hoy)); // 5
        tienda.registrarCompra(new Compra("C2", "123", 500, hoy)); // 5
        tienda.registrarCompra(new Compra("C3", "123", 500, hoy)); // 5 + 10 bonus

        assertEquals(25, cliente.getPuntos()); // 5 + 5 + (5+10)
        assertEquals(1, cliente.getStreakDias());
    }

    @Test
    public void givenClienteWithManyPoints_whenCompra_thenLevelUp() {
        cliente.setPuntos(1490); // Casi Oro

        tienda.registrarCompra(new Compra("C10", "123", 1000, LocalDate.now())); // 10 pts

        assertTrue(cliente.getPuntos() >= 1500);
        assertEquals("Oro", cliente.getNivel());
    }

    @Test
    public void givenInvalidClienteId_whenCompra_thenIgnored() {
        Compra compra = new Compra("X1", "999", 1000, LocalDate.now()); // ID no existe

        tienda.registrarCompra(compra);

        assertEquals(0, tienda.getCompras().size()); // No se agrega
    }
}
