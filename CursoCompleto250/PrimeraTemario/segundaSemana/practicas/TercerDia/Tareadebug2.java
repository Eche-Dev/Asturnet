                                        //Debug code smell 

static int factorial(int m) {                                 // Aqui es (int n) y esta (int m)
    return (n <= 1) ? 1 : n * factorial(n - 1);               // Habias ; en medio lo que hacia que el programa saltara linea asi que puse el correcto :
    }

static boolean esPrimo(int n) {                                
    if (n < 2) 
        return false;
    for (int i = 2; i * i <= n; i++) {                         //la l no pinta nada aqui, tiene que ser i.
        if (n % i == 0) 
            return false;
    }

    return true;
}