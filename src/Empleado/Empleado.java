package Empleado;

import java.util.ArrayList;
import java.util.Scanner;

//Creamos la clase abstracta de Empleado
abstract class Empleado implements Impuesto {

    protected String nombre;
    protected int id;
    protected double sueldoBase;

    //Sus constructores
    public Empleado() {
    }

    public Empleado(String nombre, int id){
        this.nombre = nombre;
        this.id = id;
    }

    public Empleado(String nombre, int id, double sueldoBase) {
        this.nombre = nombre;
        this.id = id;
        this.sueldoBase = sueldoBase;
    }

    //Sus métodos
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldo(double sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public abstract double calcularSueldo();

    @Override
    public double calcularImpuesto() {
        return 0;
    }
}

//Clase hija de Empleado y utilizando la interfaz impuesto
class EmpleadoAsalariado extends Empleado implements Impuesto{

    public EmpleadoAsalariado() {
    }

    public EmpleadoAsalariado(String nombre, int id) {
        super(nombre, id);

    }

    @Override
    public double calcularSueldo() {
        return 250000;
    }

    @Override
    public double calcularImpuesto() {
        return calcularSueldo()*0.08;
    }
}

//Clase hija de Empleado y utilizando la interfaz Impuesto
class EmpleadoComision extends Empleado implements Impuesto{

    private double ventasRealizadas;

    public EmpleadoComision() {
    }

    public EmpleadoComision(String nombre, int id, double sueldoBase, double ventasRealizadas) {
        super(nombre, id, sueldoBase);
        this.ventasRealizadas = ventasRealizadas;
    }

    @Override
    public double calcularSueldo() {

        return sueldoBase + (ventasRealizadas * 0.1);
    }

    @Override
    public double calcularImpuesto() {
        return calcularSueldo() * 0.1;
    }
}

//Clase hija de Empleado y utilizando la interfaz Impuesto
class EmpleadoPorHoras extends Empleado implements Impuesto{

    private int horasTrabajadas;

    public EmpleadoPorHoras() {
    }

    public EmpleadoPorHoras(String nombre, int id, double sueldoBase, int horasTrabajadas) {
        super(nombre, id, sueldoBase);
        this.horasTrabajadas = horasTrabajadas;
    }

    @Override
    public double calcularSueldo() {
        return sueldoBase + (horasTrabajadas * 1000);
    }

    @Override
    public double calcularImpuesto() {
        return calcularSueldo()*0.05;
    }
}

interface Impuesto {

    public double calcularImpuesto();


}


class GestorEmpleados {

    //Creo el ArrayList y su constructor.
    ArrayList<Empleado> empleados;

    public GestorEmpleados() {
        empleados = new ArrayList<Empleado>();
    }


    //Creo los métodos para agregar, eliminar y modificar empleados.
    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public void eliminarEmpleado(int id) {
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getId() == id) {
                empleados.remove(i);
                break;
            }
        }

    }

    public void modificarEmpleado(String nuevoNombre, double nuevoSueldo, int id) {
        for (Empleado empleado : empleados) {
            if (empleado.getId() == id) {
                empleado.setNombre(nuevoNombre);
                empleado.setSueldo(nuevoSueldo);
                break;
            }
        }
    }

    //Creo otro método para verificar empleado que más adelante me va a servir para corroborar de no eliminar/modificar un empleado cuya id no exista.
    public boolean verificarEmpleado(int id) {
        for (Empleado empleado : empleados) {
            if (empleado.getId() == id) {
                return true;
            }
        }
        return false;
    }
}

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorEmpleados empleados = new GestorEmpleados(); //Utilizo el GestorEmpleados para ir guardando los datos de los empleados en el ArrayList

        //Aplico un bucle while donde el usuario elige cuando dejar de usarlo.
        while (true) {

            //Excepción para que no se me ingrese una opción que no sea numerica..
            try {
                System.out.println("1. Agregar empleado\n2. Eliminar empleado\n3. Modificar empleado\n4. Calcular sueldos e impuestos\n5. Salir");
                System.out.print("Seleccione una opción: ");
                int opcion = Integer.parseInt(scanner.nextLine());


                switch (opcion) {
                    case 1:
                        //Excepción para que no se me ingrese una opción que no sea numerica.
                        try {
                            System.out.println("Ingrese ID del empleado");
                            int idEmpleado = Integer.parseInt(scanner.nextLine());


                            System.out.println("Ingrese nombre del empleado");
                            String nombreEmpleado = scanner.nextLine();

                            System.out.println("1. Empleado Asalariado\n2. Empleado por comisión\n3. Empleado por horas");
                            //Excepción para que no se me ingrese una opción que no sea numerica.
                            try {
                                System.out.print("Seleccione una opción: ");
                                int tipoEmpleado = Integer.parseInt(scanner.nextLine());


                                switch (tipoEmpleado) {
                                    case 1:
                                        EmpleadoAsalariado empleadoasalariado = new EmpleadoAsalariado(nombreEmpleado, idEmpleado);
                                        empleados.agregarEmpleado(empleadoasalariado);
                                        break;
                                    case 2:
                                        System.out.println("Ingrese sueldo base del empleado: ");
                                        double sueldoEmpleado = scanner.nextDouble();
                                        scanner.nextLine();
                                        //Excepción para que no se ingrese un número negativo para el sueldo del empleado.
                                        try {
                                            if (sueldoEmpleado < 0) {
                                                throw new IllegalArgumentException("El sueldo del empleado no puede ser negativo.\n");
                                            }
                                        } catch (IllegalArgumentException e) {
                                            System.out.println("Error: " + e.getMessage());
                                            break;
                                        }
                                        System.out.println("Ingrese la cantidad en ventas realizadas");
                                        double cantVentas = scanner.nextDouble();
                                        scanner.nextLine();
                                        //Excepción para que no se ingrese una cantidad de ventas negativas del empleado por comisión.
                                        try {
                                            if (cantVentas < 0) {
                                                throw new IllegalArgumentException("La cantidad de ventas del empleado no pueden ser negativas.\n");
                                            }
                                        } catch (IllegalArgumentException e) {
                                            System.out.println("Error: " + e.getMessage());
                                            break;
                                        }
                                        EmpleadoComision empleadocomision = new EmpleadoComision(nombreEmpleado, idEmpleado, sueldoEmpleado, cantVentas);
                                        empleados.agregarEmpleado(empleadocomision);
                                        break;
                                    case 3:
                                        System.out.println("Ingrese sueldo base del empleado: ");
                                        sueldoEmpleado = scanner.nextDouble();
                                        scanner.nextLine();
                                        //Excepción para que no se ingrese un valor negativo para el sueldo del empleado.
                                        try {
                                            if (sueldoEmpleado < 0) {
                                                throw new IllegalArgumentException("El sueldo del empleado no puede ser negativo.\n");
                                            }
                                        } catch (IllegalArgumentException e) {
                                            System.out.println("Error: " + e.getMessage());
                                            break;
                                        }
                                        System.out.println("Ingrese las horas trabajadas");
                                        int horasTrabajadas = scanner.nextInt();
                                        scanner.nextLine();
                                        //Excepción para que no se ingrese una cantidad negativa para las horas trabajadas del Empleado por horas.
                                        try {
                                            if (horasTrabajadas < 0) {
                                                throw new IllegalArgumentException("La cantidad de horas trabajadas no pueden ser negativas.\n");
                                            }
                                        } catch (IllegalArgumentException e) {
                                            System.out.println("Error: " + e.getMessage());
                                            break;
                                        }
                                        EmpleadoPorHoras empleadoporhoras = new EmpleadoPorHoras(nombreEmpleado, idEmpleado, sueldoEmpleado, horasTrabajadas);
                                        empleados.agregarEmpleado(empleadoporhoras);
                                        break;
                                    default:
                                        System.out.println("Ingresaste una opción no válida");
                                        break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Error: solo se permiten opciones numéricas. \n");
                                break;
                            }
                            System.out.println("Empleado agregado.\n");
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Error: solo se permiten opciones numéricas.\n");
                            break;
                        }
                    case 2:
                        System.out.println("Ingrese el ID del empleado a eliminar");
                        int idAEliminar = scanner.nextInt();
                        scanner.nextLine();
                        //Acá es donde uso el método de verificar empleado y una excepción para no intentar eliminar una id que no exista.
                        try {
                            if (!empleados.verificarEmpleado(idAEliminar)) {
                                throw new Exception("No existe empleado con el ID ingresado.\n");
                            }
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                            break;
                        }
                        empleados.eliminarEmpleado(idAEliminar);
                        System.out.println("Empleado eliminado");
                        break;
                    case 3:
                        System.out.println("Ingrese el ID del empleado a editar");
                        int idAEditar = scanner.nextInt();
                        scanner.nextLine();
                        //Acá uso el mismo método de verificar empleado y una excepción para no intentar modificar una id que no exista.
                        try {
                            if (!empleados.verificarEmpleado(idAEditar)) {
                                throw new Exception("No existe empleado con el ID ingresado.\n");
                            }
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                            break;
                        }
                        System.out.println("Ingrese el nuevo nombre del empleado");
                        String nuevoNombre = scanner.nextLine();
                        System.out.println("Ingrese el nuevo sueldo del empleado");
                        double nuevoSueldo = scanner.nextDouble();
                        scanner.nextLine();
                        empleados.modificarEmpleado(nuevoNombre, nuevoSueldo, idAEditar);
                        break;
                    case 4:
                        System.out.println(empleados);
                        break;
                    case 5:
                        System.out.println("Cerrando programa");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Ingresaste una opción no válida\n");
                        break;
                }
            } catch (NumberFormatException exc) {
                System.out.println("Solo se permiten opciones numéricas.\n");
            }
        }


    }
}
