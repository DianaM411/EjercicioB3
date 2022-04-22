package com.diana;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


/*Implementa un programa que pida al usuario un nombre de archivo A para lectura y otro nombre
de archivo B para escritura. Leerá el contenido del archivo A (por ejemplo ‘usa_personas.txt’) y lo
escribirá ordenado alfabéticamente en B (por ejemplo ‘usa_personas_sorted.txt’).
*/

public class Main {

    public static void main(String[] args) {
        Scanner sc = null;
        FileWriter escribir = null;

        try {//pedimos el fichero a LEER
            System.out.println("Que fichero quieres leer (ruta absoluta)?");
            sc = new Scanner(System.in); // Escaner de entrada

            //manera sencilla de cargar todas las líneas de un archivo en un ArrayList
            ArrayList<String> contenidoArchivoA = new ArrayList<>(Files.readAllLines(Paths.get(sc.nextLine())));
            Collections.sort(contenidoArchivoA);  //ordenar alfabeticamente el Arraylist

            //pedimos el fichero a ESCRIBIR
           System.out.println("En que fichero quieres escribir (ruta absoluta)?");
           File archivoB = new File(sc.nextLine());
           escribir = new FileWriter(archivoB);
           BufferedWriter escritura = new BufferedWriter(escribir);

           //escribimos el contenido del Arraylist ordenado en el archivoB
            for(int i=0;i<contenidoArchivoA.size();i++){
                escritura.write(contenidoArchivoA.get(i));
                escritura.newLine();
            }
            escribir.close();
            escritura.close();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
