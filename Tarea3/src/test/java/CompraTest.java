import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CompraTest {

    @Test
    public void givenMonto1000NivelBronce_whenCalcularPuntos_then10() {
        Compra compra = new Compra("C1", "001", 1000, LocalDate.now());
        assertEquals(10, compra.calcularPuntosBase("Bronce"));
    }

    @Test
    public void givenMonto1000NivelPlata_whenCalcularPuntos_then12() {
        Compra compra = new Compra("C2", "001", 1000, LocalDate.now());
        assertEquals(12, compra.calcularPuntosBase("Plata"));
    }

    @Test
    public void givenMonto1000NivelOro_whenCalcularPuntos_then15() {
        Compra compra = new Compra("C3", "001", 1000, LocalDate.now());
        assertEquals(15, compra.calcularPuntosBase("Oro"));
    }

    @Test
    public void givenMonto1000NivelPlatino_whenCalcularPuntos_then20() {
        Compra compra = new Compra("C4", "001", 1000, LocalDate.now());
        assertEquals(20, compra.calcularPuntosBase("Platino"));
    }

    @Test
    public void givenMonto270NivelBronce_whenCalcularPuntos_then2() {
        Compra compra = new Compra("C5", "001", 270, LocalDate.now());
        assertEquals(2, compra.calcularPuntosBase("Bronce")); // 2.7 ⇒ 2
    }
}
