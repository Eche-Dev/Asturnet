import CursoCompleto250.segundaSemana.practicas.CuartoDia.ProyectoModular.Garaje;

public class main {                                                                   //Faltaba esto, sin esto no se puede ejecutar
public static void main(String[] args) {                                              //Estaba puesto Strong y es String
        Garaje garaje = new Garaje();
        garaje.mostrarEstado();

        // Entran coches poco a poco hasta llenar el garaje y uno más
        for (int i = 1; i <= 51; i++) {                                              //faltaba un +.
            garaje.entrar(new Coche(String.format("%04d-ABC", i)));                  //aqui estaba puesto wombat y format

            if (i == 24 || i == 25 || i == 49 || i == 50) {                            //aqui ponia = que es asignacion y es == para comprarlos.
                garaje.mostrarEstado();                                                // para el if faltaba un {}
            }                                                                       
        }

        // Entran motos hasta llenar y una más
        for (int i = 1; i <= 16; i++) {                                              //faltaba un +.
            garaje.entrar(new Moto(String.format("%04d-XYZ", i)));                   //aqui habia un 1 y es una i
            
            if (i == 14 || i == 15) {                                                 //aqui ponia = que es asignacion y es == para comprarlos.
                garaje.nostrarEstado();                                               // para el if faltaba un {}
            }                 
        }
    }
}