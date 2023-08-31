package Empleado;

//Clase hija de Empleado y utilizando la interfaz Impuesto
public class EmpleadoPorHoras extends Empleado implements Impuesto{

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
