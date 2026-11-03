// ============================================================================
// GESTIÓN DE CURSO - EJERCICIO PRÁCTICO
// ============================================================================
// ENUNCIADO:
// - El profesor tiene un curso y los alumnos se matriculan, se dan de baja, aprueban o suspenden.
// - El alumno tiene que poder inscribirse en el curso, darse de baja, pidiendo sus datos.
// - Al alumno el profesor es el que lo aprueba o lo suspende asignándole una nota.
//
// ----------------------------------------------------------------------------
// 🔍 EXPLICACIÓN DEL ERROR EN LÍNEA 32 Y LÍNEA 87:
// No era un fallo de compilación de sintaxis, sino una EXCEPCIÓN EN TIEMPO DE EJECUCIÓN 
// (java.lang.IllegalArgumentException) que saltaba al ejecutar el main:
//
// 1. En la línea 84 hiciste:
//    new gestionDeCurso("Profesor Ejemplo", "Alumno123", "2024-01-15", "", 0.0);
//    Pasaste fechaBaja vacía (""), lo cual es NORMAL porque un alumno nuevo NO está de baja.
//
// 2. Pero en tu constructor (antigua línea 31-33) tenías:
//    if (fechaBaja == null || fechaBaja.isEmpty()) {
//        throw new IllegalArgumentException("La fecha de baja no puede estar vacía");
//    }
//    ¡Obligabas a que fechaBaja tuviera valor! Esto hacía que el programa explotara
//    en la línea 32 al crearse el objeto y nunca llegaba a imprimir la línea 87.
//
// 3. Además había una contradicción: tu método matricularAlumno() exigía que fechaBaja 
//    fuese null o vacía para estar matriculado. ¡Nunca podía estar matriculado si el constructor
//    obligaba a poner fecha de baja!
// ============================================================================

package EjercicioPracticos.LasDelProfe;

import java.util.Scanner;

public class gestionDeCurso {

    // ------------------------------------------------------------------------
    // ATRIBUTOS
    // ------------------------------------------------------------------------
    // protected permite acceso desde subclases o dentro del mismo paquete.
    protected String nombreProfesor;
    protected String alumnoMatricula;
    protected String fechaMatricula;
    protected String fechaBaja;        // Puede ser null o vacío si el alumno sigue activo
    protected double notaFinal;

    // ------------------------------------------------------------------------
    // CONSTRUCTORES
    // ------------------------------------------------------------------------

    // [MEJORA 1]: Constructor por defecto (sin parámetros).
    // Permite instanciar el curso vacío y rellenar los datos luego con pedirDatos().
    public gestionDeCurso() {
        this.nombreProfesor = "Sin asignar";
        this.alumnoMatricula = "Sin matrícula";
        this.fechaMatricula = "";
        this.fechaBaja = "";
        this.notaFinal = 0.0;
    }

    // [MEJORA 2]: Constructor común para matricular a un alumno nuevo (sin baja).
    public gestionDeCurso(String nombreProfesor, String alumnoMatricula, String fechaMatricula) {
        this(nombreProfesor, alumnoMatricula, fechaMatricula, "", 0.0);
    }

    // [CORRECCIÓN LÍNEA 32]: Constructor completo.
    // Ya NO exigimos que fechaBaja tenga texto. Si un alumno entra al curso, fechaBaja es "".
    public gestionDeCurso(String nombreProfesor, String alumnoMatricula, String fechaMatricula, String fechaBaja, double notaFinal) {
        
        if (nombreProfesor == null || nombreProfesor.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del profesor no puede estar vacío");
        }
        if (alumnoMatricula == null || alumnoMatricula.trim().isEmpty()) {
            throw new IllegalArgumentException("La matrícula del alumno no puede estar vacía");
        }
        if (fechaMatricula == null || fechaMatricula.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha de matrícula no puede estar vacía");
        }
        
        // CORREGIDO: fechaBaja puede ser null o vacía (significa que está activo).
        // Solo nos aseguramos de que no sea null internamente para evitar NullPointerException.
        this.fechaBaja = (fechaBaja == null) ? "" : fechaBaja.trim();

        if (notaFinal < 0 || notaFinal > 10) {
            throw new IllegalArgumentException("La nota final debe estar entre 0 y 10");
        }

        this.nombreProfesor = nombreProfesor.trim();
        this.alumnoMatricula = alumnoMatricula.trim();
        this.fechaMatricula = fechaMatricula.trim();
        this.notaFinal = notaFinal;
    }

    // ------------------------------------------------------------------------
    // MÉTODOS DE ENTRADA DE DATOS (CONSOLA)
    // ------------------------------------------------------------------------
    public void pedirDatos() { 
        Scanner sc = new Scanner(System.in);

        System.out.println("\n--- FORMULARIO DE GESTIÓN DE CURSO ---");

        System.out.print("Ingrese el nombre del profesor: ");
        this.nombreProfesor = sc.nextLine().trim();

        System.out.print("Ingrese la matrícula / DNI del alumno: ");
        this.alumnoMatricula = sc.nextLine().trim();

        System.out.print("Ingrese la fecha de matrícula (ej. 2024-01-15): ");
        this.fechaMatricula = sc.nextLine().trim();

        System.out.print("Ingrese la fecha de baja (deje en blanco si sigue activo): ");
        this.fechaBaja = sc.nextLine().trim();

        // Control de nota: pedimos un número válido entre 0 y 10
        double notaTemp = -1;
        while (notaTemp < 0 || notaTemp > 10) {
            System.out.print("Ingrese la nota final del alumno (0 - 10): ");
            String inputNota = sc.nextLine().trim().replace(',', '.'); // Permite coma o punto decimal
            try {
                notaTemp = Double.parseDouble(inputNota);
                if (notaTemp < 0 || notaTemp > 10) {
                    System.out.println("❌ Error: la nota debe estar comprendida entre 0.0 y 10.0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: por favor ingrese un número válido.");
            }
        }
        this.notaFinal = notaTemp;
        System.out.println("✅ Datos registrados correctamente.\n");
    }

    // ------------------------------------------------------------------------
    // MÉTODOS DE ACCIÓN (Lógica de Negocio que te faltaba)
    // ------------------------------------------------------------------------

    // Acción para dar de baja a un alumno asignándole una fecha
    public void darDeBaja(String fechaBaja) {
        if (fechaBaja == null || fechaBaja.trim().isEmpty()) {
            System.out.println("❌ No se puede dar de baja sin indicar una fecha.");
            return;
        }
        this.fechaBaja = fechaBaja.trim();
        System.out.println("ℹ️ El alumno " + this.alumnoMatricula + " ha sido dado de baja el " + this.fechaBaja);
    }

    // Acción para rematricular o matricular
    public void matricular(String fechaMatricula) {
        if (fechaMatricula == null || fechaMatricula.trim().isEmpty()) {
            System.out.println("❌ Se requiere una fecha de matrícula válida.");
            return;
        }
        this.fechaMatricula = fechaMatricula.trim();
        this.fechaBaja = ""; // Se anula la baja si existía
        System.out.println("ℹ️ El alumno " + this.alumnoMatricula + " ha sido matriculado el " + this.fechaMatricula);
    }

    // Acción: El profesor califica al alumno (aprueba o suspende según nota)
    public void calificar(double nota) {
        if (nota < 0 || nota > 10) {
            System.out.println("❌ Error: la nota debe estar entre 0 y 10.");
            return;
        }
        this.notaFinal = nota;
        System.out.println("👨‍🏫 El profesor " + this.nombreProfesor + " ha calificado con un " + this.notaFinal);
    }

    // ------------------------------------------------------------------------
    // MÉTODOS DE CONSULTA Y ESTADO
    // ------------------------------------------------------------------------

    // Devuelve true si el alumno está actualmente activo en el curso
    public boolean matricularAlumno() {
        return this.fechaMatricula != null && !this.fechaMatricula.isEmpty() 
            && (this.fechaBaja == null || this.fechaBaja.isEmpty());
    }

    // Devuelve true si el alumno causó baja
    public boolean darDeBajaAlumno() {
        return this.fechaBaja != null && !this.fechaBaja.isEmpty();
    }

    // Comprueba si está aprobado
    public boolean alumnoAprobado() {
        return this.notaFinal >= 5.0;
    }       

    // Comprueba si está suspendido
    public boolean alumnoSuspendido() {            
        return this.notaFinal < 5.0;
    }   

    // [CORRECCIÓN]: En tu código anterior devolvía 'this.notaFinal >= 5' duplicando alumnoAprobado().
    // Ahora devuelve un texto descriptivo: "APROBADO" o "SUSPENDIDO".
    public String alumnoAprobadoOSuspendido() {        
        return (this.notaFinal >= 5.0) ? "APROBADO" : "SUSPENDIDO";
    }

    // Muestra un resumen visual completo de la ficha del alumno
    public void mostrarFicha() {
        System.out.println("=========================================");
        System.out.println("📋 FICHA DE CONTROL DEL CURSO");
        System.out.println("=========================================");
        System.out.println("Profesor:          " + this.nombreProfesor);
        System.out.println("Alumno:            " + this.alumnoMatricula);
        System.out.println("Fecha Matrícula:   " + (this.fechaMatricula.isEmpty() ? "No matriculado" : this.fechaMatricula));
        System.out.println("Fecha Baja:        " + (this.fechaBaja.isEmpty() ? "Ninguna (Activo)" : this.fechaBaja));
        System.out.println("Estado Matrícula:  " + (matricularAlumno() ? "ACTIVO" : "DE BAJA"));
        System.out.println("Nota Final:        " + this.notaFinal);
        System.out.println("Estado Académico:  " + alumnoAprobadoOSuspendido());
        System.out.println("=========================================\n");
    }

    // ------------------------------------------------------------------------
    // GETTERS Y SETTERS (Encapsulamiento)
    // ------------------------------------------------------------------------
    public String getNombreProfesor() { return nombreProfesor; }
    public void setNombreProfesor(String nombreProfesor) { this.nombreProfesor = nombreProfesor; }

    public String getAlumnoMatricula() { return alumnoMatricula; }
    public void setAlumnoMatricula(String alumnoMatricula) { this.alumnoMatricula = alumnoMatricula; }

    public String getFechaMatricula() { return fechaMatricula; }
    public void setFechaMatricula(String fechaMatricula) { this.fechaMatricula = fechaMatricula; }

    public String getFechaBaja() { return fechaBaja; }
    public void setFechaBaja(String fechaBaja) { this.fechaBaja = fechaBaja; }

    public double getNotaFinal() { return notaFinal; }
    public void setNotaFinal(double notaFinal) {
        if (notaFinal >= 0 && notaFinal <= 10) {
            this.notaFinal = notaFinal;
        }
    }

    // ------------------------------------------------------------------------
    // MÉTODO PRINCIPAL (MAIN DE PRUEBA)
    // ------------------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("=== PRUEBA 1: Creación directa con alumno activo ===");
        // Ahora ya NO falla al poner "" en fechaBaja:
        gestionDeCurso curso1 = new gestionDeCurso("Prof. Martínez", "ALUM-2024-001", "2024-11-03", "", 8.5);
        curso1.mostrarFicha();

        System.out.println("=== PRUEBA 2: Simulación de acciones (Baja y Nota) ===");
        gestionDeCurso curso2 = new gestionDeCurso("Prof. Gómez", "ALUM-2024-002", "2024-10-01");
        curso2.mostrarFicha();

        // El profesor califica con un suspenso
        curso2.calificar(4.2);
        System.out.println("¿Aprobado?: " + curso2.alumnoAprobado());
        System.out.println("¿Suspendido?: " + curso2.alumnoSuspendido());

        // El alumno se da de baja
        curso2.darDeBaja("2024-11-03");
        curso2.mostrarFicha();

        // [OPCIONAL]: Descomenta las siguientes dos líneas si quieres probar la entrada por teclado:
        // gestionDeCurso cursoTeclado = new gestionDeCurso();
        // cursoTeclado.pedirDatos();
        // cursoTeclado.mostrarFicha();
    }
}
