import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class GestorMundial {
    private ArrayList<Jugador>  jugadores;
    private ArrayList<Tecnico>  tecnicos;
    private Scanner              sc;
    private int                  contadorId;

    public GestorMundial() {
        jugadores  = new ArrayList<>();
        tecnicos   = new ArrayList<>();
        sc         = new Scanner(System.in);
        contadorId = 1;
    }

    // ══════════════ CREATE ══════════════
    public void agregarJugador() {
        System.out.println("\n➕ REGISTRAR NUEVO JUGADOR");
        try {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine().trim();

            System.out.print("Edad: ");
            int edad = leerEntero("Edad", 15, 50);

            System.out.print("Nacionalidad: ");
            String nac = sc.nextLine().trim();

            System.out.print("Posición (Delantero/Mediocampista/Defensa/Portero): ");
            String pos = sc.nextLine().trim();

            System.out.print("Dorsal (1-99): ");
            int dorsal = leerEntero("Dorsal", 1, 99);

            System.out.print("Equipo: ");
            String equipo = sc.nextLine().trim();

            // Crear el objeto y agregarlo al ArrayList
            Jugador j = new Jugador(contadorId++, nombre, edad, nac, pos, dorsal, equipo);
            jugadores.add(j); // ← ADD al ArrayList

            System.out.println("✅ Jugador registrado. Total: " + jugadores.size());

        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error de validación: " + e.getMessage());
        }
    }



    public void listarJugadores(){
        System.out.println("\n📋 LISTA DE JUGADORES (" + jugadores.size() + " registrados)");
        if (jugadores.isEmpty()) {
            System.out.println("⚠️ No hay jugadores registrados aún.");
            return;
        }
        for (Jugador j : jugadores){
            System.out.println(j.describir()); // POLIMORFISMO
            System.out.println("─────────────────────");
        }
    }

    public Jugador buscarJugadorPorId(int id) throws JugadorNoEncontradoException {
        for (Jugador j : jugadores) {
            if (j.getId() == id) return j; // encontrado
        }
        throw new JugadorNoEncontradoException(id); // excepción personalizada
    }

    public void eliminarJugador() {
        System.out.print("\n🗑️ ID del jugador a eliminar: ");
        try {
            int id    = leerEntero("ID", 1, Integer.MAX_VALUE);
            Jugador j = buscarJugadorPorId(id);

            jugadores.remove(j); // ← REMOVE del ArrayList
            System.out.println("✅ Jugador '" + j.getNombre() + "' eliminado.");
            System.out.println("   Quedan: " + jugadores.size() + " jugadores.");

        } catch (JugadorNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarEstadisticas() {
        System.out.println("\n📊 ESTADÍSTICAS DEL MUNDIAL 2026");
        System.out.println("Total jugadores: " + jugadores.size());

        int    totalGoles = 0;
        Jugador topGoleador = null;

        for (Jugador j : jugadores) {
            totalGoles += j.getGoles();
            if (topGoleador == null || j.getGoles() > topGoleador.getGoles())
                topGoleador = j;
        }

        System.out.println("Total goles: " + totalGoles);
        if (topGoleador != null)
            System.out.println("🥇 Goleador: " + topGoleador.getNombre()
                    + " con " + topGoleador.getGoles() + " goles");
    }

    public void buscarYMostrar() {
        System.out.println("\n🔍 BUSCAR JUGADOR POR ID");
        try {
            int idBuscado = leerEntero("ID", 1, Integer.MAX_VALUE);
            Jugador j = buscarJugadorPorId(idBuscado);
            System.out.println("\n✅ JUGADOR ENCONTRADO");
            System.out.println(j.describir());

        } catch (JugadorNoEncontradoException e) {
            System.out.println("❌ " + e.getMessage());

        }
    }

    public void actualizarGoles() {
        System.out.println("\n⚽ ACTUALIZAR GOLES");
        try {
            int id = leerEntero("ID del jugador", 1, Integer.MAX_VALUE);
            Jugador j = buscarJugadorPorId(id);
            int nuevosGoles = leerEntero("Nuevo total de goles", 0, 500);
            j.setGoles(nuevosGoles);
            System.out.println("✅ Goles actualizados correctamente.");
            System.out.println(j.describir());
        } catch (JugadorNoEncontradoException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    // ══════════════ EXPORTAR DATOS ══════════════
    public void exportarTodos() {
        System.out.println("\n📤 EXPORTANDO DATOS CSV");
        if (jugadores.isEmpty()) {
            System.out.println("⚠️ No hay jugadores registrados.");
            return;
        }
        System.out.println("\nID,NOMBRE,NACIONALIDAD,POSICION,DORSAL,GOLES,ASISTENCIAS,EQUIPO");
        for (Jugador j : jugadores) {
            System.out.println(j.exportarCSV());
        }
        System.out.println("\n✅ Exportación completada.");
    }

    private int leerEntero(String campo, int min, int max) {
        while (true) { // Repite hasta obtener un valor válido
            System.out.print(campo + " (" + min + "-" + max + "): ");
            String texto = sc.nextLine().trim();
            try {
                // CONVERSIÓN: String → int
                int valor = Integer.parseInt(texto);

                // VALIDACIÓN de rango
                if (valor < min || valor > max) {
                    System.out.println("⚠️ Debe estar entre " + min + " y " + max);
                    continue; // vuelve a pedir
                }
                return valor; // valor válido

            } catch (NumberFormatException e) {
                // CAPTURA cuando el texto no es número ("abc", "", "1.5")
                System.out.println("❌ '" + texto + "' no es un número entero válido.");
            }
        }
    }


    private double leerDouble(String campo, double min, double max) {
        while (true) {
            System.out.print(campo + ": ");
            String texto = sc.nextLine().trim();
            try {
                // CONVERSIÓN: String → double
                double valor = Double.parseDouble(texto);
                if (valor < min || valor > max) {
                    System.out.println("⚠️ Fuera de rango");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("❌ No es un número decimal válido.");
            }
        }
    }
}
