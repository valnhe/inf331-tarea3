public class Cliente {
    private String id;
    private String nombre;
    private String correo;
    private int puntos;
    private String nivel;
    private int streakDias;

    public Cliente(String id, String nombre, String correo) {
        if (!correo.contains("@")) {
            throw new IllegalArgumentException("Correo inválido");
        }
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.puntos = 0;
        this.nivel = "Bronce";
        this.streakDias = 0;
    }

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        if (!correo.contains("@")) {
            throw new IllegalArgumentException("Correo inválido");
        }
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntos() {
        return puntos;
    }
    
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getNivel() {
        return nivel;
    }
    
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
   
    public int getStreakDias() {
        return streakDias;
    }

    public void setStreakDias(int streakDias) {
        this.streakDias = streakDias;
    }

    public void verPuntosyNivel() {
        System.out.println("................................................................");
        System.out.println("Cliente: " + nombre + " (ID: " + id + ")");
        System.out.println("Puntos acumulados: " + puntos);
        System.out.println("Nivel actual: " + nivel);
        System.out.println("................................................................");
    }
}

