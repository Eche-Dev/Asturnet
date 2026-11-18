class MyThread extends Thread 
{
  	// Cambiando el metodo run
  	@Override
    public void run() // Contiene el codigo ejecutado por el hilo.
                        //Llamar run no crea un hilo nuevo 
    {
        for (int i = 0; i < 5; i++) 
        {
            System.out.println(Thread.currentThread().getName()
                               + " - cuenta : " + i);
            
          	try {
              	// Espera 500 ms
                Thread.sleep(500); 
            } 
          	catch (InterruptedException e) {
                System.out.println("Hilo interrumpido");
            }
        }
    }
}


public class Main 
{
    public static void main(String[] args) 
    {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();

        thread1.setName("Hilo 1"); 
        thread2.setName("Hilo 2");

      	// Empezar hilo 1
        thread1.start();  //Se utiliza para empezar un hilo de ejecucion nuevo, 
        //Asigna recursos al nuevo hilo 
        //Llama a run() 
      
      	// EmpezAr hilo 2
        thread2.start(); 
    }
}


Hilo - flujo de ejecucion 
Los hilos comparten la memoria del proceso.
Pool de hilos (Thread pool) Grupo de hilos


Nuevo - Creado pero no iniciado new Thread()
Ejecutable - Listo start()
En ejecucion - La CPU lo esta ejecutando
Bloqueado / idle - Esperando un recurso, join () , sleep (5000)
Terminado - Su metodo (run) ha finalizado


Start() // Se utiliza para empezar un hilo de ejecucion nuevo, asigna recursos al nuevo hilo
run()   //Contiene el codigo ejecutado por el hilo. 
join()  //Esperar a que otro hilo acabe
yield() //Indica al organizador de hilos que el hilo actual esta dispuesto a pararse
interrupt() //Interrumpe el hilo actual
    isInterrupted() //Revisa si esta interrumpido, no hace nada
    interrupted() //Revisa si esta interrumpido, luego lo quita
isAlive()  //Revisa si el hilo esta "con vida"/funcionando
get/set getName() //Se nombran hilos etc...
currentThread()  // Da acceso al hilo que se esta ejecutando actualmente


AutoCloseable  /   close()
//Un objeto que puede mantener recursos hasta que cierra


AtomicInteger
//int con un valor que puede ser actualizado automaticamente.
//Tambien evita que dos hilos modifiquen el contador


public synchronized void retirar (int cantidad)
//Syncronized controla el acceso a recursos compartidos cuando varios hilos se ejecutan a la vez
//Se asegura que solamente un hilo puede ejecutar retirar() a la vez


lock hilo =...;
hilo.lock();

try {
    /////// añadir el recurso protegido por esta cerradura
}
finaly {
    .unlock();
}


condicion de carrera
if (n == 2)
{numero = n - 1}


int- primitivo - variables de tipo int almacenan un valor (5)
  
integrer - clase - variables de tipo integrer almacenan referencias a objetos integrer 


La clase integrer envuelve un valor del tipo primitivo int en un objeto. 
Un objeto de tipo Integrer contiene un solo campo cuyo tipo es int



import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
  public static void main(String[] args) {
    // Se abre recurso dentro de try
    try (FileOutputStream output = new FileOutputStream("archivoprueba.txt")) {
        //Se crea un output para modificar el archivo TXT.
      output.write("Hola".getBytes());
      // No hay necesidad de cerrar con close()
      System.out.println("Se escribio en el archivo.");
    } catch (IOException e) {
      System.out.println("Error.");
    }
  }
}


try (Scanner lector = new Scanner("Hola java"));
{
    System.out.println(lector.nextLine());
}

//Crea el scanner en el propio try y lo desecha cuando acaba de ejecutar