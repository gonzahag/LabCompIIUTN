package StreamDeDatos;

import java.io.FileReader;

public class leeDatos {

    public static void main(String args[]){
        try {
            FileReader entrada = new FileReader("archivo.txt");

            int caracter = entrada.read();
            char letra = (char)caracter;

            while (caracter != -1){
                System.out.println(letra);
                caracter = entrada.read();
                letra = (char)caracter;

            }
            entrada.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }




}
