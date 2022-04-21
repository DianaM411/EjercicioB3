package com.diana;

import java.io.*;
import java.text.Collator;
import java.util.Scanner;
import java.util.Vector;

/*Implementa un programa que pida al usuario un nombre de archivo A para lectura y otro nombre
de archivo B para escritura. Leerá el contenido del archivo A (por ejemplo ‘usa_personas.txt’) y lo
escribirá ordenado alfabéticamente en B (por ejemplo ‘usa_personas_sorted.txt’).
*/

public class Main {

    //método que reciba por parámetro un nombre de archivo para dejar sus líneas ordenadas alfabéticamente
    // (no distinguir minúsculas de mayúsculas a la hora de ordenar)
    static void cargarCodigo(String nombre){

        // Propiedades
        FileReader fill = null;
        BufferedReader bF = null;
        FileWriter fW = null;
        BufferedWriter bW = null;
        String linea;
        Vector<String> cadena = new Vector<String>(); // Uso vector en vez de array por las posiciones variables
        Collator compara = Collator.getInstance(); // Permite un orden alfabético real
        compara.setStrength(Collator.PRIMARY); // Indicamos que trate igual a mayúsculas y minusculas lleven o no acento

        // Código
        try{
            fill = new FileReader(nombre);
            bF = new BufferedReader(fill);
            linea = bF.readLine();

            // Inserto lineas de fichero en el vector
            while (linea != null){
                cadena.add(linea);
                linea = bF.readLine();
            }

            for(int i = 0; i < cadena.size(); i++){
                for (int j = 0; j < cadena.size(); j++){
                    /*
                     * Método compare devuelve:
                     *   <0 si primero < segundo
                     *   0 si iguales
                     *   >0 si primero > segundo
                     */
                    if (compara.compare(cadena.get(i), cadena.get(j)) < 0){
                        linea = cadena.get(j);
                        cadena.set(j, cadena.get(i));
                        cadena.set(i, linea);
                    }
                } // Fin segundo for
            } // Fin primer for

            // Escribo el vector en el fichero
            fW = new FileWriter(nombre);
            bW = new BufferedWriter(fW);

            for (int i = 0; i < cadena.size(); i++){
                bW.write(cadena.get(i));
                bW.newLine();
            }

            System.out.println("Ordenación completada");

        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                bF.close();
                fill.close();
                bW.close();
                fW.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }

//******************************** MAIN ***************************************

    public static void main(String[] args) {
        Scanner sc = null;
        File archivoA;
        File archivoB;

        try {
            System.out.println("Que fichero quieres leer?");
            sc = new Scanner(System.in); // Escaner de entrada
            cargarCodigo(sc.nextLine());
            sc.close();





           // System.out.println("En que fichero quieres escribir?");
          //  archivoB = new File(sc.nextLine());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
