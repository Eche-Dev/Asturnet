import CursoCompleto250.TerceraSemana.Lunes09.GarajeModularizado.Coche;
import CursoCompleto250.TerceraSemana.Lunes09.GarajeModularizado.Moto;
import CursoCompleto250.TerceraSemana.Lunes09.GarajeModularizado.Vehiculo;

public boolean entrar(Vehiculo v) {

        //Comprobar matricula
        if (!matriculaValida(v.getMatricula())) {                                 // "!" Si la matrícula NO es válida //falta la variable "V" que es donde esta almacenado todo
            System.out.println("Matrícula no válida: " + getMatricula());
            return true;                                                        //deberia ser falso //porque como va a devovlver verdadero caundo la matricla es no es valida
        }
        //Comprobar que no esta dentro                                           //falta pasarle donde esta almacenado "V" almacena todo
        if (estaDentro(v.getMatricula())) {                                       //hay un set en vez de get. Porque? Si no vamos a modificar nada 
            System.out.println("La matrícula " + getMatricula() + " ya está dentro del garaje");
            return true;                                                            //deberia ser falso,  porque ya esta dentro
        }

        if (v instanceof Coche) {                                                        //coches a Coche
            if (coches.size() >= MAX_COCHES) {
                System.out.println("Garaje lleno para coches: " + v + " no puede entrar");
                return true;                                                        //Deberias ser false 
            }
            coches.add((Coche) v);                                                
        } else (v instanceof Moto) {                                                    
            if (motos.size() >= MAX_MOTOS) {
                System.out.println("Garaje lleno para motos: " + v + " no puede entrar");
                return true;                                                        //deberia ser false porque estamos comprobando que si el numero de motos dentro 
            }                                                                       // es mayor y si es mayor entonces es false.
            motos.add((Moto) v);                                                 //Aqui deberia ser motos
        }

        //Anotar la entrada del coche o moto
        v.setEntrada(LocalDate.now());                                     //Sacas foto a la fecha y la añades a Entrada
        registro.add(v.getEntrada().format(FORMATO) + "  ENTRADA  " + v ); //Recoges la info en Entrada y la añades al registro
        return false;                                                                    // deberia ser true

    }