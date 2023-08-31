package Empleado;

import java.util.ArrayList;

public class GestorEmpleados {

    //Creo el ArrayList y su constructor.
    ArrayList<Empleado> empleados;

    public GestorEmpleados(){
        empleados = new ArrayList<Empleado>();
    }


    //Creo los métodos para agregar, eliminar y modificar empleados.
    public void agregarEmpleado(Empleado empleado){
        empleados.add(empleado);
    }

    public void eliminarEmpleado(int id){
        for(int i = 0; i < empleados.size(); i++){
            if(empleados.get(i).getId() == id){
                empleados.remove(i);
                break;
            }
        }

    }

    public void modificarEmpleado(String nuevoNombre, double nuevoSueldo, int id){
        for(Empleado empleado : empleados){
            if(empleado.getId() == id){
                empleado.setNombre(nuevoNombre);
                empleado.setSueldo(nuevoSueldo);
                break;
            }
        }
    }


    //Creo otro método para verificar empleado que más adelante me va a servir para corroborar de no eliminar/modificar un empleado cuya id no exista.
    public boolean verificarEmpleado(int id){
        for (Empleado empleado : empleados){
            if(empleado.getId() == id){
                return true;
            }
        }
        return false;
    }

    public int size(){
        return empleados.size();
    }

    //Método toString para imprimir los datos de los empleados y calcular su sueldo e impuestos a pagar.
    public String toString() {
        String todosLosEmpleados = "\nLista de empleados: \n";
        for (int i = 0; i < empleados.size(); i++){
            todosLosEmpleados += "ID "+empleados.get(i).getId() + " Nombre: "+empleados.get(i).getNombre()+" // Sueldo: "+empleados.get(i).calcularSueldo()+" // Impuesto a pagar: "+empleados.get(i).calcularImpuesto()+"\n";
        }
        return todosLosEmpleados;
    }
}
