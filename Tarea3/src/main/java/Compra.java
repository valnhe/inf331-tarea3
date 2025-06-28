import java.time.LocalDate;

public class Compra {
    private String idCompra;
    private String idCliente;
    private int monto;
    private LocalDate fecha;

    public Compra(String idCompra, String idCliente, int monto, LocalDate fecha) {
        this.idCompra = idCompra;
        this.idCliente = idCliente;
        this.monto = monto;
        this.fecha = fecha;
    }

    public String getIdCompra() {
        return idCompra;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public int getMonto() {
        return monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    // 🔢 Regla base de puntaje
    public int calcularPuntosBase(String nivel) {
        int puntosBase = monto / 100; // redondeo hacia abajo
        double multiplicador = 1.0; //Bronce

        if (nivel.equals("Plata")) {
            multiplicador = 1.2;
        } else if (nivel.equals("Oro")) {
            multiplicador = 1.5;
        } else if (nivel.equals("Platino")) {
            multiplicador = 2.0;
        }

        return (int) Math.floor(puntosBase * multiplicador);
    }
}
