public class Tecnico extends Persona implements Calificable{
    private String tactica;
    private int añosExperiencia;
    private int partidosGanados;
    private int partidosTotales;
    private String equipo;


    public Tecnico(int id, String nombre, int edad, String nacionalidad, String tactica, int añosExperiencia, int partidosGanados, int partidosTotales, String equipo) {
        super(id, nombre, edad, nacionalidad);
        this.tactica = tactica;
        this.añosExperiencia = añosExperiencia;
        this.partidosGanados = 0;
        this.partidosTotales = 0;
        this.equipo = equipo;
    }

    public String getTactica() {
        return tactica;
    }

    public int getAñosExperiencia() {
        return añosExperiencia;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public int getPartidosTotales() {
        return partidosTotales;
    }

    public String getEquipo() {
        return equipo;
    }

    public double calcularEfectividad(){
        if (partidosTotales == 0)
            return 0.0;
        return ((double) partidosGanados/partidosTotales)*100;
    }

    @Override
    public double calcularCalificacion() {
        double efectividad = calcularEfectividad();
        double bonusExp = añosExperiencia * 0.1;
        return Math.min((efectividad / 10.0) + bonusExp, 10.0);
    }

    @Override
    public String getNivelRendimiento() {
        double c = calcularCalificacion();
        return c >= 7 ? "🏆 ÉLITE" : c >= 5 ? "✅ COMPETENTE" : "⚠️ EN DESARROLLO";
    }

    @Override
    public String describir() {
        return "🎽 TÉCNICO: " + toString()
                + "\n   Táctica: " + tactica
                + " | Experiencia: " + añosExperiencia + " años"
                + " | Equipo: " + equipo
                + "\n   Efectividad: " + String.format("%.1f%%", calcularEfectividad())
                + " — " + getNivelRendimiento();
    }
}
