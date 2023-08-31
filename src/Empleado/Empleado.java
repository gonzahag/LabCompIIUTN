package Empleado;

//Creamos la clase abstracta de Empleado
public abstract class Empleado implements Impuesto {

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
