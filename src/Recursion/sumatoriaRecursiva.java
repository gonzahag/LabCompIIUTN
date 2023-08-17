package Recursion;

import java.util.Scanner;

public class sumatoriaRecursiva {

    public static int sumatoria(int numero){
        if (numero <= 0){
            return 0;
        } else {
            return numero + sumatoria(numero - 1);
        }
    }

    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese un número para calcular su sumatoria");
        int numero = scanner.nextInt();
        System.out.println("Sumatoria de "+numero+": "+sumatoria(numero));
    }

}
