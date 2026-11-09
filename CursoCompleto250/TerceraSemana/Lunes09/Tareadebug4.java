    public boolean entrar(Vehiculo v) {
        f (v instanceof  Coche) {                                                                   //Estaba mas el instanceof.
            if (coche.size() >= MAX_COCHES) {
                System.out.println("Garaje lleno para coches: " + v + " no puede entrar");
                return false;
            }
            coches.add((Coche) v);
        } else (v instanceof  Moto) {                                                               //Estaba mas el instanceof.
            if (motos.size() >= MAX_MOTOS) {
                System.out.printIn("Garaje lleno para motos: " + v + " no puede entrar");           
                return false;
            }
            motos.add((Moto) v);                                                                    //Aqui es v y estaba m
        }
        return true; 
    }