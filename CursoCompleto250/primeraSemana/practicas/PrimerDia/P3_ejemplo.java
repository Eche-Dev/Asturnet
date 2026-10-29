package CursoCompleto250.primeraSemana.practicas.PrimerDia;
import java.util.Scanner;

// Este ejercicio es practica mia, reforzando lo aprendido en la primera semana del curso de Java de aspasia. 
// para entrar con todo a este de asturnet
// Este programa solicita al usuario ingresar el nombre de un trabajador y calculara su salario semanal, 
// considerando que trabaja 40 horas a la semana y su pago por hora es de $15.00.

public class P3_ejemplo {
    
    public static void main(String[] args) {
    
        System.out.println("================================");   //LINEA PARA VERLO BONITO
        System.out.println("Calculadora de salario semanales");     //Damos la la bienveida al usuario solamente con un println.
        System.out.println("================================");  //LINEA PARA VERLO BONITO

        Scanner empresa = new Scanner(System.in);   //Creamos un objeto Scanner para leer la entrada del usuario

        //Solicitamos al usuario que ingrese el nombre del trabajador, el pago por hora y el número de horas trabajadas en la semana
        System.out.print("Ingrese el nombre del trabajador: ");
        String nombreTrabajador = empresa.nextLine();
        
        System.out.print("Ingrese el pago por hora del trabajador: ");
        double pagoPorHora = empresa.nextDouble();  

        System.out.print("Ingrese el número de horas trabajadas en la semana: ");
        double horasTrabajadas = empresa.nextDouble();  

        //Ya tenemos lo que nos pide el programa porque el usuario lo ha ingresado arriba,ahora calculamos el salario semanal aqui abajo.
        System.out.println("=============NOMINA========");  //LINEA PARA VERLO BONITO
        System.out.println("Nombre del trabajador: " + nombreTrabajador);

        double salarioSemanal = pagoPorHora * horasTrabajadas;
        System.out.println("El salario semanal bruto es: $" + salarioSemanal);
        
        double Retencion = (salarioSemanal * 10 / 100); 
        System.out.println("Retencion del (10%) es: $" + Retencion);

        double salarioFinal = salarioSemanal - Retencion;
        System.out.printf("El salario final de es: $%.2f%n",salarioFinal);
        
        System.out.println("================================");  //LINEA PARA VERLO BONITO
        // Calculamos el salario anual multiplicando el salario final por 52 semanas
        double salarioAnual = salarioFinal * 52; 
        System.out.printf("El salario anual de %s es: $%.2f%n", nombreTrabajador, salarioAnual);
        

    }
    
}
