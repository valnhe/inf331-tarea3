import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ClienteTest {

    @Test
    public void givenValidData_whenCreatingCliente_thenAttributesAreSetCorrectly() {
        // Given - When
        Cliente cliente = new Cliente("123", "Ana", "ana@gmail.com");

        // Then
        assertEquals("123", cliente.getId());
        assertEquals("Ana", cliente.getNombre());
        assertEquals("ana@gmail.com", cliente.getCorreo());
        assertEquals(0, cliente.getPuntos());
        assertEquals("Bronce", cliente.getNivel());
        assertEquals(0, cliente.getStreakDias());
    }

    @Test
    public void givenInvalidEmail_whenCreatingCliente_thenThrowsException() {
        // Given - When - Then
        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("123", "Ana", "correo.invalido");
        });
    }

    @Test
    public void givenCliente_whenUpdatingNombre_thenNombreIsUpdated() {
        Cliente cliente = new Cliente("123", "Ana", "ana@mail.com");
        cliente.setNombre("Valentina");
        assertEquals("Valentina", cliente.getNombre());
    }

    @Test
    public void givenCliente_whenUpdatingCorreoToValid_thenCorreoIsUpdated() {
        Cliente cliente = new Cliente("123", "Ana", "ana@mail.com");
        cliente.setCorreo("nueva@mail.com");
        assertEquals("nueva@mail.com", cliente.getCorreo());
    }

    @Test
    public void givenCliente_whenUpdatingCorreoToInvalid_thenThrowsException() {
        Cliente cliente = new Cliente("123", "Ana", "ana@mail.com");
        assertThrows(IllegalArgumentException.class, () -> {
            cliente.setCorreo("correo_sin_arroba.cl");
        });
    }

    @Test
    public void givenCliente_whenUpdatingPuntosAndNivel_thenValuesChange() {
        Cliente cliente = new Cliente("123", "Ana", "ana@mail.com");
        cliente.setPuntos(1000);
        cliente.setNivel("Plata");
        assertEquals(1000, cliente.getPuntos());
        assertEquals("Plata", cliente.getNivel());
    }

    @Test
    public void givenCliente_whenUpdatingStreakDias_thenValueChanges() {
        Cliente cliente = new Cliente("123", "Ana", "ana@mail.com");
        cliente.setStreakDias(2);
        assertEquals(2, cliente.getStreakDias());
    }
}
