package Recursion;

public class factorialSobrecarga {

    public static int factorial(int numero){
        if (numero == 1 || numero == 0){
            return 1;
        } else {
            return numero * factorial(numero - 1);
        }
    }

    public static int factorial(int numero, boolean recursion){
        int resultado = 1;
        if(!recursion){
            for (int i = 1; i <= numero; i++){
                resultado *= i;
            }
        }
        return resultado;
    }


    public static void main(String args[]){
        int numero = 6;

        System.out.println(factorial(6));
        System.out.println(factorial(6, false));
    }

}
