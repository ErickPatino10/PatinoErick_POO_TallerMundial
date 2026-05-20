import java.io.Serializable;

public class Jugador extends Persona implements Calificable, Exportable {
    private String posicion;
    private int    dorsal;
    private int    goles;
    private int    asistencias;
    private int    minutosJugados;
    private String equipo;


    public Jugador(int id, String nombre, int edad,
                   String nacionalidad,
                   String posicion,
                   int dorsal,
                   String equipo) {

        super(id, nombre, edad, nacionalidad);

        this.posicion = posicion;

        if (dorsal < 1 || dorsal > 99)
            throw new IllegalArgumentException("El dorsal debe estar entre 1 y 99");

        this.dorsal = dorsal;
        this.goles = 0;
        this.asistencias = 0;
        this.minutosJugados = 0;
        this.equipo = equipo;
    }

    public String getPosicion() {
        return posicion;
    }

    public int getDorsal() {
        return dorsal;
    }

    public int getGoles() {
        return goles;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public int getMinutosJugados() {
        return minutosJugados;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    @Override
    public double calcularCalificacion() {
        double calif = (goles * 3.0) + (asistencias * 1.5) + ((minutosJugados / 90.0) * 0.5);
        return Math.min(calif, 10.0);
    }

    @Override
    public String getNivelRendimiento() {
        double c = calcularCalificacion();
        if (c >= 8)
            return "⭐ ESTRELLA";
        if (c >= 6)
            return "✅ BUENO";
        if (c >= 4)
            return "⚠️ REGULAR";
        return "❌ BAJO";
    }

    @Override
    public String exportarDatos() {
        return "Jugador: " + getNombre() + " | Equipo: " + equipo
                + " | Goles: " + goles + " | Calif: "
                + String.format("%.2f", calcularCalificacion());
    }

    @Override
    public String exportarCSV() {
        return getId() + "," + getNombre() + ","
                + getNacionalidad() + "," + posicion + ","
                + dorsal + "," + goles + ","
                + asistencias + "," + equipo;
    }

    @Override
    public String describir() {
        return "⚽ JUGADOR: " + toString()
                + "\n   Posición: " + posicion
                + " | Dorsal: #" + dorsal
                + " | Equipo: " + equipo
                + "\n   Goles: " + goles
                + " | Asistencias: " + asistencias
                + " | Minutos: " + minutosJugados
                + "\n   Calificación: " + String.format("%.2f", calcularCalificacion())
                + " — " + getNivelRendimiento();
    }
}
