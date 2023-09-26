package ArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ej1 {

    public static void main(String args[]){
        ArrayList <Integer> miLista = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Scanner scanner = new Scanner(System.in);
        boolean error = false;


        while(!error) {
            try {
                System.out.println("Ingrese un número para agregarlo al ArrayList: ");
                int n = Integer.parseInt(scanner.nextLine());
                miLista.add(n);
                error = true;
            } catch (NumberFormatException exc) {
                System.out.println("Solo se permiten opciones numéricas.");
            }
        }
        error = false;

        while(!error) {
            try {
                System.out.println("Ingrese un índice para mostrar el número que se encuentra en esa posición:");
                int posicion = Integer.parseInt(scanner.nextLine());
                System.out.println(miLista.get(posicion));
                error = true;
            } catch (NumberFormatException exc) {
                System.out.println("Solo se permiten opciones numéricas.");
            } catch (IndexOutOfBoundsException exc) {
                System.out.println("Ingresó un número fuera del rango.");
            }
        }
        error = false;

        while(!error) {
            try {
                System.out.println("\nIngrese un índice para eliminar el número que se encuentre en esa posición:");
                int indice = Integer.parseInt(scanner.nextLine());
                miLista.remove(indice);
                error = true;
            } catch (NumberFormatException exc) {
                System.out.println("Solo se permiten opciones numéricas.");
            } catch (IndexOutOfBoundsException exc) {
                System.out.println("Ingresó un número fuera de rango.");
            }
        }

        System.out.println("ArrayList después de todas las operaciones:\n"+miLista);



    }

}
