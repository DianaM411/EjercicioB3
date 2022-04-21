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

    // Método que reciba un array de cadenas de caracteres y vuelque su contenido a un archivo
    public static void escribeArray(String[] palabras, String nombre){
        // Propiedades
        FileWriter fich = null;

        // Código
        try{
            fich = new FileWriter(nombre);
            for(int i = 0; i < palabras.length; i++){
                fich.write(palabras[i]);
                // Condicion que evita insertar un * al final del todoo
                if (i + 1 != palabras.length)
                    fich.write("*");
            }
            // Muestra por pantalla proceso finalizado
            System.out.println("Palabras insertadas correctamente");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                fich.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }

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
        String linea;
        FileReader fill = null;
        BufferedReader bF = null;
        Vector<String> contenido = new Vector<String>(); // Uso vector en vez de array por las posiciones variables

        try {
            fill = new FileReader(archivoA);
            bF = new BufferedReader(fill);
            linea = bF.readLine();

            System.out.println("Que fichero quieres leer?");
            sc = new Scanner(System.in); // Escaner de entrada
            archivoA = new File(sc.nextLine());

            // Inserto lineas de fichero en el vector
            while (linea != null){
                contenido.add(linea);
                linea = bF.readLine();
            }
            sc.close();





           System.out.println("En que fichero quieres escribir?");
           archivoB = new File(sc.nextLine());
            escribeArray(contenido, "archivoB");
            cargarCodigo("archivoB");
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
