public class JugadorNoEncontradoException extends RuntimeException {

    public JugadorNoEncontradoException(int id) {
        super("No se encontró jugador con ID: " + id);
    }

    public JugadorNoEncontradoException(String message) {
        super(message);
    }
}
