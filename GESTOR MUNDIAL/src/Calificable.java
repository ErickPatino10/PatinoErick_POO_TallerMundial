public interface Calificable {

    double CALIFICACION_MAXIMA = 10.0;
    double CALIFICACION_MINIMA = 0.0;

    double calcularCalificacion();
    String getNivelRendimiento();

    default boolean superaPromedioMundial() {
        return calcularCalificacion() >= 6.0;
    }
}
