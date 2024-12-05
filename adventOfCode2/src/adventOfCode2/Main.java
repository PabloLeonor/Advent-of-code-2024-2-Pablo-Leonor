/**
 * 
 * Advent of Code 2024 day 2 By Pablo Leonor by Pablo Leonor is marked with CC0 1.0 Universal 
 * 
 * **/

package adventOfCode2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner tomaDatos = new Scanner(System.in);
        String ubicacionEntrada;

        // Preguntamos al usuario por la ubicación del input
        //Lets ask the user for input location
        System.out.println("Saludos usuario, para continuar, por favor localice el fichero del input (ubicacion+input.txt): ");
        ubicacionEntrada = tomaDatos.nextLine();

        if (ubicacionEntrada.endsWith(".txt")) {
            try {
                FileReader fr = new FileReader(ubicacionEntrada);
                BufferedReader bf = new BufferedReader(fr);
                StringBuilder contenido = new StringBuilder();
                String sCadena;
                int numeroDeLineas = 0;

                // Leer el archivo línea por línea
                //read file per line
                while ((sCadena = bf.readLine()) != null) {
                    contenido.append(sCadena).append("\n");
                    numeroDeLineas++;
                }
                bf.close();

                String contenidoEntrada = contenido.toString();
                System.out.println("Contenido del archivo:\n" + contenidoEntrada);
                System.out.println("Número total de líneas: " + numeroDeLineas);

                // iniciamos la matriz para almacenar los datos
                //lets start the data matrix
                String[][] listaContenidoEntrada = new String[numeroDeLineas][10];
                String[] listaTrozo = contenidoEntrada.split("\n");


                for (int i = 0; i < numeroDeLineas; i++) {
                    String[] numerosComoTexto = listaTrozo[i].split(" ");
                    System.out.print("\nLínea " + i + ": ");
                    for (int j = 0; j < numerosComoTexto.length; j++) {
                        listaContenidoEntrada[i][j] = numerosComoTexto[j].trim();
                        System.out.print("-" + listaContenidoEntrada[i][j]);
                    }
                }
             // iniciamos el contador de aciertos
                //lets create the success counter
                int[] contadorDeAciertos = new int[numeroDeLineas]; // 0 inseguro, 1 seguro
                int aciertosBuenos=0;
                for (int i = 0; i < numeroDeLineas; i++) {
                    System.out.println("\nIteración #" + i + "[");

                    boolean esSeguro = true;
                    boolean ascendente = false; 
                    boolean direccionDefinida = false; 

                    for (int j = 0; j < listaTrozo[i].split(" ").length - 1; j++) {
                        try {
                            int primerNumero = Integer.parseInt(listaContenidoEntrada[i][j]);
                            int segundoNumero = Integer.parseInt(listaContenidoEntrada[i][j + 1]);

                            // se repiten los números?
                            //are the numbers repeating?
                            if (primerNumero == segundoNumero) {
                                esSeguro = false; 
                                break;
                            }

                            // se ve la distancia
                            //lets check the distance
                            int diferencia = Math.abs(primerNumero - segundoNumero);
                            if (diferencia < 1 || diferencia > 3) {
                                esSeguro = false; 
                                break;
                            }

                            // la cosa es que si no hay una dirección se pone
                            //if there is no direction setted, then lets set it
                            if (!direccionDefinida) {
                                ascendente = primerNumero < segundoNumero;
                                direccionDefinida = true;
                            }

                            // comprobamos si la dirección cambia
                            //lets check if the direction changes
                            if ((ascendente && primerNumero > segundoNumero) || (!ascendente && primerNumero < segundoNumero)) {
                                esSeguro = false;
                                break;
                            }
                        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                            esSeguro = false;
                            break;
                        }
                    }

                    // ahora, lo que hacemos es asignarlo todo
                    //now we have to asign everything
                    if (esSeguro) {
                        contadorDeAciertos[i] = 1;
                    } else {
                        contadorDeAciertos[i] = 0;
                    }

                    if (esSeguro) {
                        System.out.println("/! ES SEGURO]");
                        aciertosBuenos++;
                    } else {
                        System.out.println("/! ES INSEGURO]");
                    }
                }

                //resultado!
                System.out.println("En total hay: "+aciertosBuenos);
                
         
            } catch (IOException ioe) {
                System.err.println("Error al leer el archivo. Verifica la ubicación.");
                ioe.printStackTrace();
            }
        } else {
            System.out.println("Ubicación o tipo de archivo no válido");
        }

        tomaDatos.close();
    }
}
