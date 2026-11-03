package CursoCompleto250.segundaSemana.practicas.PrimerDia;

public class practicaT3  {                                                            

    static class Empleado {                                                        
        protected final String nombre;
        protected final double salarioBase;
        
        
        Empleado (String nombre, double salarioBase) {
            this.nombre = nombre;
            this.salarioBase = salarioBase;
            }                                                                        //Faltaba una llave "}" para cerrar, Actuazlizado y pongo el }.✅

            double calcularNomina() {                                               //lo formatee para que se vea mejor.
                return salarioBase;
            }

            @Override                                                               //lo formatee para que se vea mejor.
            public String toString(){
                return getClass().getSimpleName() + " " + nombre;
            }
        }

                                                    //En todas esta mal Empleados y lo cambio a Empleado.✅


    class EmpleadoFijo extends Empleado {                                       //El void aqui es incorrecto porque aqui esperamos un return, lo elimino y pongo solo class.✅
        private final int trienios;

        EmpleadoFijo(String nombre, double salarioBase, int trienios) {
            super(nombre, salarioBase);
            this.trienios = trienios;
        }

        @Override                                                                       
        double calcularNomina() {
            return salarioBase + 50 * trienios;                                   //lo formatee y puse un ; al final.✅
        }    
    }

    
    class EmpleadoTemporal extends Empleado {                                    //El void aqui es incorrecto porque aqui esperamos un return, lo elimino y pongo solo class.✅
        private final int mesesContrato;

        EmpleadoTemporal (String nombre, double salarioBase, int meses) {
            super(nombre, salarioBase);
            this.mesesContrato = meses;
        }

        @Override 
        double calcularNomina() {
            return salarioBase;
        }
    }


    class Directivo extends Empleado {                                              //El void aqui es incorrecto porque aqui esperamos un return, lo elimino y pongo solo class.✅                           
        private final double bonus;

        Directivo (String nombre, double salarioBase, double bonus) {                //cambien int a double en el parametro de bonus porque es un double.✅
            super(nombre, salarioBase);
            this.bonus = bonus;}

        @Override 
        double calcularNomina() {
            return salarioBase + bonus;}                                //lo formatee y cambie la trampa de : puse un ; al final.✅
    }
    }
                                                        //aqui habia una llave de mas, la elimino.✅

//lo he hecho en vs code con extensiones y me ha corregido los errores de sintaxis algunos, el formato se lo di yo, ademas de que he puesto comentarios para explicar los cambios que he hecho. paso a paso.

//Nota al intentar compilar me ha dado este error.

// PS ProyectosVScode\Asturnet> cd ProyectosVScode\Asturnet\CursoCompleto250\segundaSemana\Tareas                
// PS ProyectosVScode\Asturnet\CursoCompleto250\segundaSemana\Tareas> javac practicaT3.java                                                                                                           
// practicaT3.java:3: error: cannot find symbol
// import java.util.list;                                                             //eh investigado y es porque el paquete no existe, lo correcto es java.util.List con L mayuscula.✅                                             
//                 ^
//   symbol:   class list
//   location: package java.util
// 1 error
// PS ProyectosVScode\Asturnet\CursoCompleto250\segundaSemana\Tareas> 



// Nota: Segundo intento 


// PS ProyectosVScode\Asturnet\CursoCompleto250\segundaSemana\Tareas> javac practicaT3.java                     //ahora si compila sin errores.✅
// PS ProyectosVScode\Asturnet\CursoCompleto250\segundaSemana\Tareas> Java practicaT3.java 
// error: can't find main(String[]) method in class: CursoCompleto250.segundaSemana.Tareas.practicaT3           // el problema es que no hay un metodo main, por donde java comienza a ejecutar el programa.
// PS ProyectosVScode\Asturnet\CursoCompleto250\segundaSemana\Tareas>  

