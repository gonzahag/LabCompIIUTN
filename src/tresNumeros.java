import java.util.Scanner;

public class tresNumeros {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el primer número: ");
        int primerNumero = scanner.nextInt();
        System.out.println("Ingrese el segundo número: ");
        int segundoNumero = scanner.nextInt();
        System.out.println("Ingrese el tercer número: ");
        int tercerNumero = scanner.nextInt();

        if (primerNumero > segundoNumero) {
            if (primerNumero > tercerNumero) {
                if (segundoNumero > tercerNumero) {
                    System.out.println(primerNumero + " " + segundoNumero + " " + tercerNumero);
                } else {
                    System.out.println(primerNumero + " " + tercerNumero + " " + segundoNumero);
                }
            } else {
                System.out.println(tercerNumero + " " + primerNumero + " " + segundoNumero);
            }
        } else {
            if (segundoNumero > tercerNumero) {
                if (tercerNumero > primerNumero) {
                    System.out.println(segundoNumero + " " + tercerNumero + " " + primerNumero);
                } else {
                    System.out.println(segundoNumero + " " + primerNumero + " " + tercerNumero);
                }
            } else {
                System.out.println(tercerNumero + " " + segundoNumero + " " + primerNumero);
            }
        }
    }
}