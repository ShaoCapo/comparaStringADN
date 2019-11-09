import java.lang.*;

public class Principal {

    //Resuelto usando el esquema original de las transparencias de clase de Selección Optima

    public static int numMinMutaciones(String cadena1, String cadena2) {
        numMutaciones numMutacionesMejor = new numMutaciones();
        EsSolucion solucion = new EsSolucion();
        if (cadena1.isEmpty() || cadena2.isEmpty()) {// Caso en el que alguna de las dos cadenas esta vacia
            if (cadena1.isEmpty() && cadena2.isEmpty()) {
                numMutacionesMejor.set(0);
            }else if (cadena1.isEmpty()) {
                numMutacionesMejor.set(cadena2.length());
            } else if (cadena2.isEmpty()) {
                numMutacionesMejor.set(cadena1.length());
            }
        } else {// Ambas cadenas contienen al menos un caracter
            seleccionOptima(cadena1, cadena2, solucion, 0, numMutacionesMejor);
        }
        return numMutacionesMejor.get();
    }


    public static void seleccionOptima(String cadena1, String cadena2, EsSolucion solucion, int numMutaciones, numMutaciones numMuntacionesMejor) {
        // Cadenas auxiliares donde se guardan substrings para ir pasando por todos los caracteres uno por uno
        String cadena1Aux;
        String cadena2Aux;

        int candidatos = 0; //Inicilizar conjunto candidatos;

        do {
            if (aceptable(cadena1.charAt(0), cadena2.charAt(0), candidatos)) { //Candidato aceptable
                // ANOTAR
                if (candidatos == 0) { //Sustituir
                    cadena1Aux = cadena1.substring(1);
                    cadena2Aux = cadena2.substring(1);
                    numMutaciones++;
                } else if (candidatos == 1) {//Elimnar
                    cadena1Aux = cadena1.substring(1);
                    cadena2Aux = cadena2;
                    numMutaciones++;
                } else if (candidatos == 2) { //Añadir
                    cadena1Aux = cadena1;
                    cadena2Aux = cadena2.substring(1);
                    numMutaciones++;
                } else {
                    cadena1Aux = cadena1.substring(1);
                    cadena2Aux = cadena2.substring(1);
                }

                if (cadena1Aux.isEmpty() || cadena2Aux.isEmpty()) {//Si es solucion
                    if (cadena1Aux.isEmpty() && cadena2Aux.isEmpty()) { //Caso las dos cadenas vacias
                        if (!solucion.get()) { //Posible primera solucion
                            solucion.set(true);
                            numMuntacionesMejor.set(numMutaciones);
                        } else if (numMutaciones < numMuntacionesMejor.get()) {
                            numMuntacionesMejor.set(numMutaciones);
                        }
                    } else if (cadena1Aux.isEmpty()) { //Caso una cadena vacía
                        if (!solucion.get()) {//Posible primera solucion
                            solucion.set(true);
                            numMuntacionesMejor.set(numMutaciones + cadena2Aux.length());
                        } else if (numMutaciones + cadena2Aux.length() < numMuntacionesMejor.get()) {
                            numMuntacionesMejor.set((numMutaciones + cadena2Aux.length()));
                        }
                    } else if (cadena2Aux.isEmpty()) {//Caso la otra cadena vacía
                        if (!solucion.get()) {//Posible primera solucion
                            solucion.set(true);
                            numMuntacionesMejor.set(numMutaciones + cadena1Aux.length());
                        } else if (numMutaciones + cadena1Aux.length() < numMuntacionesMejor.get()) {
                            numMuntacionesMejor.set((numMutaciones + cadena1Aux.length()));
                        }
                    }

                } else {
                    if (numMutaciones < numMuntacionesMejor.get()) //Poda.
                        seleccionOptima(cadena1Aux, cadena2Aux, solucion, numMutaciones, numMuntacionesMejor); //Avanzo de estado.
                }
                //DESANOTAR
                if (candidatos < 3) {
                    numMutaciones--;
                }
            }
            candidatos++;
        }
        while (candidatos < 4); //Si existen candidatos se ejecuta el bucle do while.
    }

    //Funcion Aceptable que verifica si son iguales o diferentes dos caracteres.
    public static boolean aceptable(char c1, char c2, int candidato) {
        if (candidato == 3) {
            return c1 == c2;
        } else return c1 != c2;
    }

}

class EsSolucion {

    private boolean solucion;

    public EsSolucion() {
        this.solucion = false;
    }

    public void set(boolean valor) {
        this.solucion = valor;
    }

    public boolean get() {
        return this.solucion;
    }

}

class numMutaciones {

    private int numMutaciones;

    public numMutaciones() {
        this.numMutaciones = Integer.MAX_VALUE;
    }

    public void set(int valor) {
        this.numMutaciones = valor;
    }

    public int get() {
        return this.numMutaciones;
    }

}
