package Empleado;

//Clase hija de Empleado y utilizando la interfaz impuesto
public class EmpleadoAsalariado extends Empleado implements Impuesto{

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
