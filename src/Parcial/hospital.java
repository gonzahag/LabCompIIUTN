package Parcial;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

abstract class Persona implements Serializable{
    private String nombre;
    private String dni;
    private String fechaNacimiento;

    public Persona(){}

    public Persona(String nombre, String dni, String fechaNacimiento){
        this.nombre = nombre;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
interface Informacion{
    void verHistorialDeEventos();
}

class Doctor extends Persona implements Serializable{
    private String especialidad;

    public Doctor(){}

    public Doctor(String nombre, String dni, String fechaNacimiento, String especialidad){
        super(nombre, dni, fechaNacimiento);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void mostrarInformacion(){
        System.out.println(getNombre()+" - "+getDni()+" - "+getFechaNacimiento()+" - "+getEspecialidad());
    }

}

class Eventos implements Serializable{
    private String fecha;
    private String observaciones;

    public Eventos(){}

    public Eventos(String fecha, String observaciones){
        this.fecha = fecha;
        this.observaciones = observaciones;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }


}


class Paciente extends Persona implements Informacion, Serializable{
    private String nroTelefono;
    private int tipoSangre;
    private ArrayList<Eventos> historial;

    public Paciente(){}

    public Paciente(String nombre, String dni, String fechaNacimiento, String nroTelefono, int tipoSangre){
        super(nombre, dni, fechaNacimiento);
        this.nroTelefono = nroTelefono;
        this.tipoSangre = tipoSangre;
        this.historial = new ArrayList<>(historial);
    }

    public String getNroTelefono() {
        return nroTelefono;
    }

    public void setNroTelefono(String nroTelefono) {
        this.nroTelefono = nroTelefono;
    }

    public int getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(int tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public ArrayList <Eventos> getHistorial() {
        return historial;
    }

    public void setHistorial(ArrayList <Eventos> historial) {
        this.historial = historial;
    }

    public void verHistorialDeEventos(){
        System.out.println("HISTORIAL MÉDICO: ");
        System.out.println(historial);
    }


}

class Hospital implements Serializable{
    private ArrayList<Doctor> doctores;
    private ArrayList<Eventos> historial;
    private ArrayList<Paciente> pacientes;

    public Hospital(){
        doctores = new ArrayList <Doctor>();
        historial = new ArrayList <Eventos>();
        pacientes = new ArrayList <Paciente>();
    }

    public void registarNuevoPaciente(Paciente paciente){
        pacientes.add(paciente);
    }

    public void editarInfoPaciente(String dni, String nuevoDni, String nuevoNombre, String nuevoNumero, String nuevaFechaNacimiento, int nuevoTipoSangre){
        for (Paciente paciente : pacientes){
            if(paciente.getDni().equals(dni)){
                paciente.setDni(nuevoDni);
                paciente.setNombre(nuevoNombre);
                paciente.setNroTelefono(nuevoNumero);
                paciente.setTipoSangre(nuevoTipoSangre);
                paciente.setFechaNacimiento(nuevaFechaNacimiento);
            }
        }
    }

    public void agregarDoctor(Doctor doctor){
        this.doctores.add(doctor);
    }

    public void nuevoHistorial(Eventos nuevoHistorial){
        historial.add(nuevoHistorial);
    }

    public void mostarListaDoctores(){
        System.out.println("Lista de doctores: ");
        for(Doctor doctor : doctores){
            doctor.mostrarInformacion();
        }
    }

    public void consultarHistorialMedico(String dni){
        for (Paciente paciente : pacientes){
            if (paciente.getDni().equals(dni)){
                paciente.verHistorialDeEventos();
            }
        }

    }

    public void guardarPacientesEnArchivo(String nombreArchivo){
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(nombreArchivo))){
            salida.writeObject(this);
            System.out.println("Pacientes guardados en un archivo exitosamente.");
        } catch (IOException e){
            System.out.println("Error al guardar las reservas en el archivo.");
        }
    }

    public static Hospital cargarPacientesDesdeArchivo(String nombreArchivo){
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(nombreArchivo))){
            return (Hospital) entrada.readObject();
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Error al cargar los pacientes desde un archivo.");
            return null;
        }
    }

}


class Main {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        Doctor doctor1 = new Doctor("Gonzalo", "87654321", "11/08/1970", "Kinesiología");
        Doctor doctor2 = new Doctor("Pedro", "12345678", "25/07/1978", "Psicología");




        Hospital hospital = new Hospital();

        int opcion;

       while(true){
           try {
               FileReader entrada = new FileReader("datos.txt");

               int caracter = entrada.read();
               char letra = (char)caracter;

               while (caracter != -1){
                   System.out.print(letra);
                   caracter = entrada.read();
                   letra = (char)caracter;

               }
               entrada.close();
           } catch (Exception e){
               e.printStackTrace();
           }
            System.out.println("\nMenú:\n1. Listar Doctores.\n2. Registar un nuevo paciente.\n3. Actualizar información personal de un paciente.\n4. Consultar el historial médico de un paciente.\n5. Nuevo historial para un paciente.\n6. Guardar historial de pacientes en archivo.\n7. Cargar historial de pacientes desde un archivo.\n8. Salir.");
            System.out.println("Elija una opción: ");
            opcion = scanner.nextInt();


            switch (opcion){
                case 1:
                    System.out.println();
                    hospital.agregarDoctor(doctor1);
                    hospital.agregarDoctor(doctor2);
                    hospital.mostarListaDoctores();
                    System.out.println();
                    break;
                case 2:
                    System.out.println("Ingrese el dni del paciente: ");
                    String dni = scanner.next();
                    System.out.println("Ingrese el nombre del paciente: ");
                    String nombre = scanner.next();
                    System.out.println("Ingrese la fecha de nacimiento (Formato dd/mm/yyyy): ");
                    String fecha = scanner.next();
                    System.out.println("Ingrese el número de teléfono: ");
                    String nroTelefono = scanner.next();
                    System.out.println("Ingrese el tipo de sangre(solo número): ");
                    int tipoSangre = scanner.nextInt();
                    hospital.registarNuevoPaciente(new Paciente(nombre, dni, fecha, nroTelefono, tipoSangre));
                    System.out.println("Paciente creado correctamente.");
                    break;
                case 3:
                    System.out.println("Ingrese el dni del paciente a modificar: ");
                    String dniAModificar = scanner.next();
                    System.out.println("Ingrese el nuevo dni del paciente: ");
                    String nuevoDni = scanner.next();
                    System.out.println("Ingrese el nuevo nombre del paciente: ");
                    String nuevoNombre = scanner.next();
                    System.out.println("Ingrese el nuevo número del paciente: ");
                    String nuevoNumero = scanner.next();
                    System.out.println("Ingrese la nueva fecha de nacimiento: ");
                    String nuevaFecha = scanner.next();
                    System.out.println("Ingrese el nuevo tipo de sangre: ");
                    int nuevoTipo = scanner.nextInt();
                    hospital.editarInfoPaciente(dniAModificar, nuevoDni, nuevoNombre, nuevoNumero, nuevaFecha, nuevoTipo);
                    System.out.println("Paciente modificado correctamente.");
                    break;
                case 4:
                    System.out.println("Ingrese el dni del paciente: ");
                    String dniABuscar = scanner.next();
                    hospital.consultarHistorialMedico(dniABuscar);
                    break;
                case 5:
                    System.out.println("Ingrese el dni del paciente: ");
                    String dniNuevoHistorial = scanner.next();
                    System.out.println("Ingrese la fecha: ");
                    String fechaNuevoHistorial = scanner.next();
                    System.out.println("Ingrese la observación: ");
                    String nuevaObservacion = scanner.next();
                    Eventos nuevoHistorial = new Eventos(fechaNuevoHistorial, nuevaObservacion);
                    hospital.nuevoHistorial(nuevoHistorial);
                    break;
                case 6:
                    hospital.guardarPacientesEnArchivo("pacientes.txt");
                    break;
                case 7:
                    hospital.cargarPacientesDesdeArchivo("pacientes.txt");
                    break;
                case 8:
                    System.out.println("Hasta luego");
                    return;
                default:
                    System.out.println("Ingresó una opcion inválida.");
            }
        }



    }

}




