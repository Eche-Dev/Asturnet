import java.util.Collection;
import java.util.ArrayDeque;
import java.util.Stack;



//Listas(list)------------------------------------------------------------

//Crear la lista Libros con contenido String
List <String> libros = new ArrayList<>();

//Añadir libros
libros.add("Java 101");
libros.add("Los pilares de la tierra");

//Consultar libros con (libros.get(NumeroIdentificativo));
System.out.println(libros.get(0));

//Conjuntos (Set)------------------------------------------------------------

//Crear el set colores
Set<String> colores = new HashSet<>();

//Añadir colores al set
colores.add("Rojo");  //0  
colores.add("Verde"); //1
colores.add("Azul");  //2
colores.add("Rojo");  //Esta la va a ignorar por estar duplicada.
colores.add("Violeta"); //3

//Mapas (Map)------------------------------------------------------------

//Crear el mapa

Map<String, Integrer> notas = new HashMap<>();
// Al añadir se usa nombreDelMapa.put(Clave, valor);
notas.put("Ana", 9);
notas.put("Juan", 5);
//Si se reutiliza la clave, sobreescribe el valor de la anterior.


//Consultar 
System.out.println(notas.get("Ana"))


//Pila (LIFO)----------------------------------------------------

//Creo pila

Deque<String> pilaEjemplo = new ArrayDeque<>();
pilaEjemplo.push("Maracas");
pilaEjemplo.push("Bajo");
pilaEjemplo.push("Ukelele");

System.out.println("Siguiente libro" + pilaEjemplo.peek()); //Devuelve Ukelele
//Peek muestra el siguiente elemento SIN BORRARLO.

System.out.println("Eliminar" + pilaEjemplo.pop()); //devuelve Ukelele y borra
//Pop Elimina y muestra el elemento en la cima de la pila.

System.out.println("Tareas por terminar " + pilaEjemplo.size());
//Mira tamaño de pila y cuenta cuantas tareas quedan.


//Cola (FIFO)----------------------------------------------------

//Creo cola
Queue<String> colaEjemplo = new LinkedList<>();
cola.add("Pitbull");
cola.add("Husky");
cola.add("Chihuahua");

System.out.println("Siguiente libro" + colaEjemplo.peek()); //Devuelve Pitbull
System.out.println("Eliminar" + colaEjemplo.poll()); //Devuelve Pitbull y elimina




Ejemplo

Boton boton = new Boton("Pulsalo");
boton.addActionListener(new ActionListener) {
    public void actionPerformed(ActionEvent e) {
        System.out.println("Has pulsado el boton");
    }
}



ActionListener 

    Jbutton
    //Humano pulsa boton o Enter con el foco puesto en el

    JList
    //Doble click en un elemento de la lista

    JMenuItem
    // Seleccionas una opcion de un menu

    JTextField
    //Pulsas Enter con el foco en un campo de texto

KeyListener
//Interacciones con teclas

FocusListener
//Recibir o perder el foco

MouseListener
//Comportamiento del raton

MouseMotionListener
//Mover el puntero sobre un elemento y click y arrastrar.