package CursoCompleto250.segundaSemana.practicas.CuartoDia.ProyectoModular;

// SemaforoLed Controla la señalizacion luminica, segun la cantidad de coches presentes en el garaje.

public class SemaforoLed {

    private String color = "VERDE";

    public void actualizarColor(int cochesDentro) {
        if (cochesDentro < 25) {
            this.color = "VERDE";
        } else if (cochesDentro < 50) {
            this.color = "NARANJA";
        } else {
            this.color = "ROJO";
        }
        System.out.println("Semáforo LED actualizado a: " + this.color);
    }

    public String getColor() {
        return this.color;
    }
}
