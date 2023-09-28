package ModeloParcial;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

abstract class Persona implements Serializable{
    protected String nombre;
    protected String dni;

    public Persona(){}

    public Persona(String nombre, String dni){
        this.nombre = nombre;
        this.dni = dni;

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

}

class Huesped extends Persona implements mostrarInformacion, Serializable{
    private String telefono;

    public Huesped(){}

    public Huesped(String nombre, String dni , String telefono){
        super(nombre, dni);
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void mostrarInformacion(){
        System.out.println("Nombre: "+getNombre()+"\nDNI: "+getDni()+"\nNúmero de teléfono: "+getTelefono());
    }

}

class Habitacion implements mostrarInformacion, Serializable{

    private int numero_habitacion;
    private int cant_camas;
    private int capac_huespedes;
    private boolean estado;
    private ArrayList<Huesped> huespedes;

    public Habitacion(){}

    public Habitacion(int numero_habitacion, int cant_camas, int capac_huespedes){
        this.numero_habitacion = numero_habitacion;
        this.cant_camas = cant_camas;
        this.capac_huespedes = capac_huespedes;
        this.estado = false;
        this.huespedes = new ArrayList<>();
    }

    public int getNumero_habitacion() {
        return numero_habitacion;
    }

    public int getCant_camas() {
        return cant_camas;
    }

    public int getCapac_huespedes() {
        return capac_huespedes;
    }

    public boolean isEstado() {
        return estado;
    }

    public ArrayList<Huesped> getHuespedes() {
        return huespedes;
    }

    public void setCant_camas(int cant_camas) {
        this.cant_camas = cant_camas;
    }

    public void setCapac_huespedes(int capac_huespedes) {
        this.capac_huespedes = capac_huespedes;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void agregarHuesped(Huesped huesped){
        this.huespedes.add(huesped);
    }

    public void quitarHuested(int indice){
        if (indice >= 0 && indice < this.huespedes.size()){
            this.huespedes.remove(indice);
        }
    }

    public void reservarHabitacion(ArrayList<Huesped> huespedes){
        this.estado = true;
        this.huespedes.addAll(huespedes);
    }

    public void cancelarReserva(){
        this.estado = false;
        this.huespedes.clear();
    }

    public void mostrarInformacion(){
        System.out.println("\nNúmero de habitación: "+getNumero_habitacion()+"\nCantidad de camas: "+getCant_camas()+"\nCapacidad de huespedes: "+getCapac_huespedes()+"\nEstado: "+ (isEstado() ? "Ocupada" : "Libre"));
        if (isEstado()){
            System.out.println("Huespedes: ");
            for (Huesped huesped : huespedes){
                huesped.mostrarInformacion();
            }
        }
    }

}

class Hotel implements Serializable{
    private String nombreHotel;
    private ArrayList<Habitacion> habitaciones;

    public Hotel(String nombreHotel){
        this.nombreHotel = nombreHotel;
        this.habitaciones = new ArrayList<>();
    }

    public String getNombre() {
        return nombreHotel;
    }

    public void setNombre(String nombre) {
        this.nombreHotel = nombre;
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void agregarHabitacion(Habitacion habitacion){
        habitaciones.add(habitacion);
    }

    public void eliminarHabitacion(Habitacion habitacion){
        habitaciones.remove(habitacion);
    }
    public void mostrarListaHabitaciones(){
        System.out.println("Lista de habitaciones: ");
        for (Habitacion habitacion : habitaciones){
            habitacion.mostrarInformacion();
            System.out.println();
        }
    }

    public void guardarReservasEnUnArchivo(String nombreArchivo){
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(nombreArchivo))){
            salida.writeObject(this);
            System.out.println("Reservas guardadas en un archivo exitosamente.");
        } catch (IOException e){
            System.out.println("Error al guardar las reservas en el archivo.");
        }
    }

    public static Hotel cargarReservasDesdeArchivo(String nombreArchivo){
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(nombreArchivo))){
            return (Hotel) entrada.readObject();
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Error al cargar las reservas desde un archivo.");
            return null;
        }
    }

}

class GestorHotel{


    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);

        File file = new File("nombreHotel.txt");
        String nombre = "";
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            nombre = reader.readLine();
        } catch (IOException e){
            System.out.println("Error al leer el archivo");
        }

        Hotel hotel = new Hotel(nombre);

        hotel.agregarHabitacion(new Habitacion(101, 2, 2));
        hotel.agregarHabitacion(new Habitacion(102, 1, 1));
        hotel.agregarHabitacion(new Habitacion(103, 3, 4));


        System.out.println("Bienvenido al Hotel "+hotel.getNombre());
        int opcion;
        while(true){
            System.out.println("Menu: ");
            System.out.println("1. Ver la lista de habitaciones.");
            System.out.println("2. Reservar una habitación.");
            System.out.println("3. Cancelar una reserva.");
            System.out.println("4. Guardar reserva en un archivo.");
            System.out.println("5. Cargar reserva desde un archivo.");
            System.out.println("6. Salir");
            System.out.println("Elija una opción");
            opcion = scanner.nextInt();

            switch(opcion){
                case 1:
                    hotel.mostrarListaHabitaciones();
                    break;
                case 2:
                    System.out.println("Ingrese el número de habitación que quiere reservar: ");
                    int nroHabitacion = scanner.nextInt();
                    System.out.println("Ingrese la cantidad de huespedes que se hospedarán: ");
                    int cantHuespedes = scanner.nextInt();
                    ArrayList<Huesped> huespedes = new ArrayList<>();
                    for(int i = 0; i < cantHuespedes; i++){
                        System.out.println("Ingrese el nombre del huesped "+(i + 1) + ": ");
                        String nombreHuesped = scanner.next();
                        System.out.println("Ingrese el DNI del huesped "+(i + 1) + ": ");
                        String dniHuesped = scanner.next();
                        System.out.println("Ingrese el número de teléfono del huesped "+(i + 1) + ": ");
                        String nroHuesped = scanner.next();
                        huespedes.add(new Huesped(nombreHuesped, dniHuesped, nroHuesped));
                    }
                    Habitacion habitacion = null;
                    for (Habitacion h : hotel.getHabitaciones()){
                        if(h.getNumero_habitacion() == nroHabitacion){
                            habitacion = h;
                            break;
                        }
                    }
                    if (habitacion != null){
                        habitacion.reservarHabitacion(huespedes);
                        System.out.println("Habitación reservada exitosamente");
                    } else {
                        System.out.println("No se encontró ninguna habitación con el número ingresado.");
                    }

                    break;

                case 3:
                    System.out.println("Ingrese el número de habitación para cancelar la reserva: ");
                    nroHabitacion = scanner.nextInt();
                    habitacion = null;
                    for (Habitacion h : hotel.getHabitaciones()){
                        if (h.getNumero_habitacion() == nroHabitacion) {
                            habitacion = h;
                            break;
                        }
                    }

                    if (habitacion != null){
                        habitacion.cancelarReserva();
                        System.out.println("La reserva se canceló exitosamente");
                    } else {
                        System.out.println("No se encontró una habitación con el número ingresado");
                }
                    break;
                case 4:
                    hotel.guardarReservasEnUnArchivo("reservas.dat");
                    break;
                case 5:
                    hotel = Hotel.cargarReservasDesdeArchivo("reservas.dat");
                    if (hotel != null){
                        System.out.println("Reservas cargadas desde archivo exitosamente.");
                    } else {
                        System.out.println("Error al cargar las reservas desde archivo.");
                    }
                    break;
                case 6:
                    System.out.println("Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida. Ingrese una opción correcta.");

            }

        }

    }

}


interface mostrarInformacion{
    void mostrarInformacion();
}
