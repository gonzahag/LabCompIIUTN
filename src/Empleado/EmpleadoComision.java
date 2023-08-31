package Empleado;

//Clase hija de Empleado y utilizando la interfaz Impuesto
public class EmpleadoComision extends Empleado implements Impuesto{

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
