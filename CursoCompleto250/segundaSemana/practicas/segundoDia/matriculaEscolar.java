package CursoCompleto250.segundaSemana.practicas.segundoDia;

//clase que representa la matricula escolar
public class matriculaEscolar {
    protected String nombreProfesor;
    protected String nombreAlumno;
    public String fechaMatricula;
    protected double notaFinal;
    private boolean activo = true; 

    //constructor de la clase matriculaEscolar
    public matriculaEscolar(String nombreProfesor, String nombreAlumno, String fechaMatricula, double notaFinal) {
        this.nombreProfesor = nombreProfesor;
        this.nombreAlumno = nombreAlumno;
        this.fechaMatricula = fechaMatricula;
        this.notaFinal = notaFinal;
    }

    //metodos para obtener los valores de los atributos de la clase matriculaEscolar

    //verificamos que tengamos un profesor asignado.
    public String NombreProfesor() {
        if (nombreProfesor == null) {
            return "No hay profesor asignado";
        } else {
            return nombreProfesor;
        }
    }

    //verificamos que no este null ni vacio y que tenga al menos 2 caracteres
    public String NombreAlumno() {
        if (nombreAlumno == null || nombreAlumno.isEmpty()) {
            return "No hay alumno asignado";
        } else if (nombreAlumno.length() < 2) {
            return "Nombre de alumno demasiado corto";
        } else {
            return nombreAlumno;
        }
    }

    //verficamos que la fecha de matricula no sea null
    public String FechaMatricula() {
        if (fechaMatricula == null) {
            return "No hay fecha de matricula asignada";
        } else {
            return fechaMatricula;
        }
    }

    //verificamos que la nota final no sea negativa
    public double NotaFinal() {
        if (notaFinal < 0) {
            return 0;
        } else {
            return notaFinal;
        }
    }

    //verificamos si el alumno esta aprobado o reprobado.
    public boolean esAprobado() {
        return notaFinal >= 5;
    }
    public boolean esReprobado() {
        return notaFinal < 5;
    }
    
    //verificamos si el alumno esta inactivo.
    public void darDeBaja() {
    this.activo = false;
}

    // verificamos si el alumno esta activo.
    public void matricular() {
    this.activo = true;
}


    //metodo main para probar la clase matriculaEscolar sin scanner.
    //imprimo los valores para ver que funcione.
    //iba a usar scanner pero no me dio tiempo, lo hare en la siguiente practica.
    public static void main(String[] args) {
    matriculaEscolar m1 = new matriculaEscolar("Prof. Martínez", "Ana Gómez", "2024-11-03", 8.5);
    System.out.println("Alumno: " + m1.NombreAlumno());
    System.out.println("Nota: " + m1.NotaFinal());
    System.out.println("Fecha de matrícula: " + m1.FechaMatricula());
    System.out.println("Resultado del curso: " + (m1.esAprobado() ? "Aprobado" : "Reprobado"));
    System.out.println("Alumno matriculado: " + (m1.activo ? "Activo" : "Inactivo"));   
}
}


//nota: Todo lo que esta aqui se como funciona y me he hecho unos apuntes geniales.
//ah sido 50% yo con mi logica y 50% el completador de copilot y de los snippets de la pagina de java.
//eh revisado algunas cosas en la documentacion de java por duda.
//estuve a un paso de metemer en bucle y else if jajaja gracias a dios hago lo minimo.
