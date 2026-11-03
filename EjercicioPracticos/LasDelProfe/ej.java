package EjercicioPracticos.LasDelProfe;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 ==============================================================================
 PARTE 1: IDENTIFICACIÓN DE CLASES CANDIDATAS Y JUSTIFICACIÓN
 ==============================================================================
 
 Texto del enunciado:
 "Una escuela gestiona cursos; cada curso lo imparte un profesor y tiene varios 
  alumnos matriculados. De cada matrícula se guarda la fecha y la nota final. 
  Los alumnos pueden matricularse y darse de baja de un curso."

 ------------------------------------------------------------------------------
 A) SUSTANTIVOS EXTRAÍDOS:
    - Escuela
    - Curso
    - Profesor
    - Alumno
    - Matrícula
    - Fecha
    - Nota final

 ------------------------------------------------------------------------------
 B) CLASES CANDIDATAS SELECCIONADAS (CON COMPORTAMIENTO Y ESTADO PROPIO):
    1. Curso: 
       - Clase fundamental. Representa la materia impartida, agrupa alumnos 
         matriculados y está asignada a un profesor.
    2. Profesor: 
       - Entidad que imparte la materia y evalúa a los alumnos.
    3. Alumno: 
       - Entidad que participa en los cursos, solicita matrículas y bajas.
    4. Matrícula: 
       - CLASE ASOCIATIVA imprescindible. Une a un Alumno con un Curso. 
         Guarda datos propios de ese vínculo temporal (fecha y nota final).
    5. Escuela (Opcional según alcance): 
       - Actúa como clase contenedora o gestora del sistema educativo.

 ------------------------------------------------------------------------------
 C) CANDIDATAS DESCARTADAS Y SU JUSTIFICACIÓN:
    ❌ Fecha:
       - JUSTIFICACIÓN: Se descarta como clase propia porque es un valor/dato 
         simple sin lógica de negocio propia. En Java se representa mediante 
         tipos estándar de la librería como `LocalDate` o `String`.
    ❌ Nota final:
       - JUSTIFICACIÓN: Se descarta como clase propia porque no tiene identidad 
         independiente ni ciclo de vida propio; es un atributo numérico (`double`) 
         que pertenece al estado de la clase `Matrícula`.
    ❌ Escuela (si el foco es solo el aula):
       - JUSTIFICACIÓN: Si el sistema solo modela el curso y las matrículas, 
         la Escuela actúa como mero contexto externo y puede omitirse para 
         mantener el diseño simple. (Aun así, la incluimos como gestora de cursos).

 ==============================================================================
 PARTE 2: IMPLEMENTACIÓN DE LAS CLASES (ATRIBUTOS Y MÉTODOS)
 ==============================================================================
*/

// 1. CLASE PROFESOR
class Profesor {
    // Atributos (mínimo 2)
    private String idProfesor;
    private String nombre;
    private String especialidad;

    public Profesor(String idProfesor, String nombre, String especialidad) {
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    // Método
    public void calificarMatricula(Matricula matricula, double nota) {
        matricula.asignarNota(nota);
        System.out.println("El profesor " + this.nombre + " ha calificado con un " + nota);
    }

    // Getters
    public String getIdProfesor() { return idProfesor; }
    public String getNombre() { return nombre; }
    public String getEspecialidad() { return especialidad; }
}


// 2. CLASE ALUMNO
class Alumno {
    // Atributos (mínimo 2)
    private String dni;
    private String nombreCompleto;
    private String email;

    public Alumno(String dni, String nombreCompleto, String email) {
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
    }

    // Métodos
    public Matricula solicitarInscripcion(Curso curso) {
        Matricula nuevaMatricula = new Matricula(this, curso, LocalDate.now());
        curso.agregarMatricula(nuevaMatricula);
        return nuevaMatricula;
    }

    public void solicitarBaja(Curso curso) {
        curso.darDeBaja(this);
    }

    // Getters
    public String getDni() { return dni; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getEmail() { return email; }
}


// 3. CLASE MATRÍCULA (Clase intermedia con atributos del vínculo)
class Matricula {
    // Atributos (mínimo 2)
    private Alumno alumno;
    private Curso curso;
    private LocalDate fechaMatricula;
    private Double notaFinal; // null si aún no fue evaluado
    private boolean activa;

    public Matricula(Alumno alumno, Curso curso, LocalDate fechaMatricula) {
        this.alumno = alumno;
        this.curso = curso;
        this.fechaMatricula = fechaMatricula;
        this.notaFinal = null;
        this.activa = true;
    }

    // Métodos
    public void asignarNota(double nota) {
        if (nota < 0.0 || nota > 10.0) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 10.");
        }
        this.notaFinal = nota;
    }

    public boolean estaAprobada() {
        return this.notaFinal != null && this.notaFinal >= 5.0;
    }

    public void cancelar() {
        this.activa = false;
    }

    // Getters
    public Alumno getAlumno() { return alumno; }
    public Curso getCurso() { return curso; }
    public LocalDate getFechaMatricula() { return fechaMatricula; }
    public Double getNotaFinal() { return notaFinal; }
    public boolean isActiva() { return activa; }
}


// 4. CLASE CURSO
class Curso {
    // Atributos (mínimo 2)
    private String codigo;
    private String titulo;
    private Profesor profesor;                    // Asociación con Profesor
    private List<Matricula> matriculas;           // Agregación/Composición de Matrículas

    public Curso(String codigo, String titulo, Profesor profesor) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.profesor = profesor;
        this.matriculas = new ArrayList<>();
    }

    // Métodos
    public void agregarMatricula(Matricula m) {
        matriculas.add(m);
        System.out.println("Alumno " + m.getAlumno().getNombreCompleto() + " matriculado en: " + this.titulo);
    }

    public void darDeBaja(Alumno alumno) {
        for (Matricula m : matriculas) {
            if (m.getAlumno().getDni().equalsIgnoreCase(alumno.getDni()) && m.isActiva()) {
                m.cancelar();
                System.out.println("Alumno " + alumno.getNombreCompleto() + " dado de baja de: " + this.titulo);
                return;
            }
        }
        System.out.println("No se encontró matrícula activa para el alumno en este curso.");
    }

    public int totalAlumnosActivos() {
        int total = 0;
        for (Matricula m : matriculas) {
            if (m.isActiva()) total++;
        }
        return total;
    }

    // Getters
    public String getCodigo() { return codigo; }
    public String getTitulo() { return titulo; }
    public Profesor getProfesor() { return profesor; }
    public List<Matricula> getMatriculas() { return matriculas; }
}


// 5. CLASE ESCUELA (Gestora contenedora)
class Escuela {
    // Atributos (mínimo 2)
    private String nombre;
    private List<Curso> catalogoCursos;

    public Escuela(String nombre) {
        this.nombre = nombre;
        this.catalogoCursos = new ArrayList<>();
    }

    // Método
    public void registrarCurso(Curso c) {
        this.catalogoCursos.add(c);
        System.out.println("Nuevo curso registrado en la escuela: " + c.getTitulo());
    }

    // Getters
    public String getNombre() { return nombre; }
    public List<Curso> getCatalogoCursos() { return catalogoCursos; }
}


// ==============================================================================
// CLASE PRINCIPAL CON MAIN PARA DEMOSTRACIÓN
// ==============================================================================
public class ej {
    public static void main(String[] args) {
        System.out.println("=== DEMOSTRACIÓN DEL MODELO DE CLASES ===\n");

        // 1. Crear escuela y profesor
        Escuela escuela = new Escuela("Asturnet Formación");
        Profesor profesor = new Profesor("P-101", "Manuel González", "Programación Java");

        // 2. Crear curso
        Curso cursoJava = new Curso("DAW-2024", "Desarrollo de Aplicaciones Web", profesor);
        escuela.registrarCurso(cursoJava);

        // 3. Crear alumnos
        Alumno alumno1 = new Alumno("12345678A", "Ana Gómez", "ana@correo.com");
        Alumno alumno2 = new Alumno("87654321B", "Carlos Díaz", "carlos@correo.com");

        // 4. Los alumnos se matriculan
        Matricula matAna = alumno1.solicitarInscripcion(cursoJava);
        Matricula matCarlos = alumno2.solicitarInscripcion(cursoJava);

        // 5. El profesor califica
        profesor.calificarMatricula(matAna, 9.0);
        System.out.println("¿Ana aprobada?: " + matAna.estaAprobada());

        // 6. Un alumno se da de baja
        alumno2.solicitarBaja(cursoJava);

        System.out.println("\nTotal alumnos activos en el curso: " + cursoJava.totalAlumnosActivos());
    }
}
