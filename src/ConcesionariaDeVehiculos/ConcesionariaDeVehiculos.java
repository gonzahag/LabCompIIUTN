package ConcesionariaDeVehiculos;

import java.io.*;
import java.util.ArrayList;

abstract class Vehiculo implements java.io.Serializable {
    protected String marca;
    protected String modelo;
    protected double precio;

    public Vehiculo(String marca, String modelo, double precio){
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public abstract double calcularImpuesto();
    public abstract void mostrarInformacion();



}


class Coche extends Vehiculo{
    public Coche(String marca, String modelo, double precio){
        super(marca, modelo, precio);
    }

    @Override
    public double calcularImpuesto() {
        return getPrecio() * 0.01;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Auto - Marca: "+getMarca()+", Modelo: "+getModelo()+", Precio: $"+getPrecio());
    }
}

class Moto extends Vehiculo{

    public Moto(String marca, String modelo, double precio){
        super(marca, modelo, precio);
    }

    @Override
    public double calcularImpuesto() {
        return getPrecio() * 0.05;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Moto - Marca: "+getMarca()+", Modelo: "+getModelo()+", Precio: $"+getPrecio());
    }
}

interface Serializable{
    void guardar() throws IOException;
    void cargar()throws IOException, ClassNotFoundException;
}

class Concesionaria implements Serializable {

    ArrayList<Vehiculo> vehiculos;

    public Concesionaria() {
        vehiculos = new ArrayList<Vehiculo>();
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
    }

    public void eliminarVehiculo(String marca, String modelo){
        for (int i = 0; i < vehiculos.size(); i++){
            if (vehiculos.get(i).getMarca().equals(marca) && vehiculos.get(i).getModelo().equals(modelo)){
                vehiculos.remove(i);
                break;
            }
        }
    }

    public void editarPrecio(String marca, String modelo, double nuevoPrecio){
        for (Vehiculo vehiculo : vehiculos){
            if(vehiculo.getMarca().equals(marca) && vehiculo.getModelo().equals(modelo)){
                vehiculo.setPrecio(nuevoPrecio);
                break;
            }
        }
    }

    public void mostrarInventario(){
        System.out.println("Vehículos: ");
        for (Vehiculo vehiculo : vehiculos){
            vehiculo.mostrarInformacion();
        }
    }

    public void guardar() throws IOException{
        try (ObjectOutputStream flujoSalida = new ObjectOutputStream(new FileOutputStream("concesionariaSerializada.txt"))) {
            flujoSalida.writeObject(vehiculos);
        }
    }

    public void cargar() throws IOException, ClassNotFoundException{
        try (ObjectInputStream flujoEntrada = new ObjectInputStream(new FileInputStream("concesionariaSerializada.txt"))){
            vehiculos = (ArrayList<Vehiculo>) flujoEntrada.readObject();
        }


    }


}

class Main{

    public static void main(String args[]){

        Coche coche1 = new Coche("Peugeot", "408", 10000);
        Coche coche2 = new Coche("Chevrolet", "Corsa", 2500);
        Moto moto1 = new Moto("Zanella", "110", 350);
        Moto moto2 = new Moto("Harley", "Davidson", 4000);

        Concesionaria concesionaria = new Concesionaria();
        concesionaria.agregarVehiculo(coche1);
        concesionaria.agregarVehiculo(coche2);
        concesionaria.agregarVehiculo(moto1);
        concesionaria.agregarVehiculo(moto2);

        concesionaria.mostrarInventario();

        try {
            concesionaria.guardar();
            System.out.println("Concesionaria Guardada");
            concesionaria = new Concesionaria();
            concesionaria.mostrarInventario();
            concesionaria.cargar();
            System.out.println("Concesionaria Cargada");
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

        concesionaria.mostrarInventario();



    }

}







