package CursoCompleto250.primeraSemana.practicas.PrimerDia;

// IMPORTACIÓN: Clase Scanner para lectura desde el teclado
import java.util.Scanner;

// CLASE: molde principal del programa P3_ejemplo
public class P3_ejemplo {
    
    // METODO main: punto de inicio del programa
    public static void main(String[] args) {
    
        // LÍNEAS DE FORMATO VISUAL
        System.out.println("================================");
        System.out.println("Calculadora de salario semanales");
        System.out.println("================================");

        // OBJETO SCANNER: captura datos desde el flujo de entrada System.in
        Scanner empresa = new Scanner(System.in);

        // ENTRADA DE DATOS: Nombre del trabajador (tipo String con nextLine)
        System.out.print("Ingrese el nombre del trabajador: ");
        String nombreTrabajador = empresa.nextLine();
        
        // ENTRADA DE DATOS: Pago por hora (tipo double con nextDouble)
        System.out.print("Ingrese el pago por hora del trabajador: ");
        double pagoPorHora = empresa.nextDouble();  

        // ENTRADA DE DATOS: Horas trabajadas (tipo double con nextDouble)
        System.out.print("Ingrese el número de horas trabajadas en la semana: ");
        double horasTrabajadas = empresa.nextDouble();  

        // CÁLCULO DE NÓMINA: Salario semanal bruto
        System.out.println("=============NOMINA========");
        System.out.println("Nombre del trabajador: " + nombreTrabajador);

        double salarioSemanal = pagoPorHora * horasTrabajadas;
        System.out.println("El salario semanal bruto es: $" + salarioSemanal);
        
        // CÁLCULO DE RETENCIÓN: 10% del salario semanal bruto
        double retencion = (salarioSemanal * 10 / 100); 
        System.out.println("Retencion del (10%) es: $" + retencion);

        // CÁLCULO DEL SALARIO NETO: Bruto menos la retención aplicada
        double salarioFinal = salarioSemanal - retencion;
        // SALIDA CON FORMATO: %.2f formatea a 2 decimales y %n da salto de línea
        System.out.printf("El salario final de es: $%.2f%n", salarioFinal);
        
        System.out.println("================================");
        // CÁLCULO ANUAL: Salario final proyectado a 52 semanas del año
        double salarioAnual = salarioFinal * 52; 
        
        // SALIDA CON FORMATO: %s recibe nombreTrabajador y %.2f recibe salarioAnual
        System.out.printf("El salario anual de %s es: $%.2f%n", nombreTrabajador, salarioAnual);
        
        // CIERRE DEL OBJETO: Buena práctica para liberar el recurso de lectura
        empresa.close();
    }
}
