// Archivo: Persona.java
public abstract class Persona {

    // ENCAPSULAMIENTO: atributos privados
    private int    id;
    private String nombre;
    private int    edad;
    private String nacionalidad;


    public Persona(int id, String nombre, int edad, String nacionalidad) {
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        if (edad < 0 || edad > 120)
            throw new IllegalArgumentException("Edad inválida: " + edad);
        this.id = id;
        this.nombre = nombre.trim();
        this.edad = edad;
        this.nacionalidad = nacionalidad;
    }

    public int    getId(){ return id; }
    public String getNombre() { return nombre; }
    public int    getEdad() { return edad; }
    public String getNacionalidad() { return nacionalidad; }


    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("Nombre inválido");
        this.nombre = nombre.trim();
    }

    public void setEdad(int edad) {
        if (edad < 0 || edad > 120)
            throw new IllegalArgumentException("Edad inválida");
        this.edad = edad;
    }

    public abstract String describir();

    public String toString() {
        return "[ID:" + id + "] " + nombre +
                " (" + edad + " años) — " + nacionalidad;
    }
}