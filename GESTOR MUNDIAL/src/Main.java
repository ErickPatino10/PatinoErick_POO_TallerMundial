import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        GestorMundial gestor = new GestorMundial();
        Scanner sc = new Scanner(System.in);
        int opcion;

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   ⚽ SISTEMA MUNDIAL FIFA 2026 🏆    ║");
        System.out.println("╚══════════════════════════════════════╝");

        do {
            System.out.println("\n═══════ MENÚ PRINCIPAL ═══════");
            System.out.println("1. ➕ Registrar Jugador");
            System.out.println("2. 📋 Listar todos los Jugadores");
            System.out.println("3. 🔍 Buscar Jugador por ID");
            System.out.println("4. ✏️ Actualizar Goles de Jugador");
            System.out.println("5. 🗑️ Eliminar Jugador");
            System.out.println("6. 📊 Ver Estadísticas");
            System.out.println("7. 📤 Exportar datos (CSV)");
            System.out.println("0. 🚪 Salir");
            System.out.print("Opción: ");

            try {
                // CONVERSIÓN: String → int con manejo de error
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Ingresa un número válido.");
                opcion = -1; // valor inválido para continuar el loop
                continue;
            }

            switch (opcion) {
                case 1 -> gestor.agregarJugador();
                case 2 -> gestor.listarJugadores();
                case 3 -> gestor.buscarYMostrar();
                case 4 -> gestor.actualizarGoles();
                case 5 -> gestor.eliminarJugador();
                case 6 -> gestor.mostrarEstadisticas();
                case 7 -> gestor.exportarTodos();


                case 0 -> System.out.println("👋 ¡Hasta el próximo Mundial!");
                default -> System.out.println("⚠️ Opción no válida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}