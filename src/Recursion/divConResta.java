package Recursion;

public class divConResta {

    //Utilizo recursión para la división con números enteros.
    public static int dividir(int dividendo, int divisor){
        if (divisor > dividendo){
            return 0;
        } else {
            return 1 + dividir(dividendo - divisor, divisor);
        }
    }

    //Creo el mismo método pero ahora con double para usar la sobrecarga de métodos. División con iteración.
    public static double dividir(double dividendo, double divisor){
        if (divisor > dividendo){
            return 0.0;
        } else {
            int cont = 0;
            while(dividendo >= divisor){
                cont++;
                dividendo-=divisor;
            }
            return cont;
        }
    }

    public static void main(String args[]){

        System.out.println(dividir(49, 7));
        System.out.println(dividir(81.0, 9.0));


    }



}
