package GestionDeCuentas;

import java.util.ArrayList;
import java.util.Scanner;

abstract class Cuenta {
    protected int numeroCuenta;
    protected double saldo;
    double SALDO_DEFAULT = 0;
    public Cuenta() {
    }

    public Cuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = SALDO_DEFAULT;
    }

    public Cuenta(int numeroCuenta, double saldo){
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public abstract void depositar(double cantidad);
    public abstract void retirar(double cantidad);
    public abstract void mostrarInformacion();
}

class CuentaPersona extends Cuenta{
    private String nombre;
    private String apellido;

    public CuentaPersona(int numeroCuenta, double saldo){
        super(numeroCuenta, saldo);
    }

    public CuentaPersona(int numeroCuenta, double saldo, String nombre, String apellido){
        super(numeroCuenta, saldo);
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public void depositar(double cantidad) {
        setSaldo(getSaldo()+cantidad);
    }

    @Override
    public void retirar(double cantidad) {
        if (cantidad <= this.saldo){
            setSaldo(getSaldo()-cantidad);
        } else {
            System.out.println("No se puede retirar más dinero del disponible.");
        }
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Número de cuenta: "+getNumeroCuenta()+"\nNombre: "+getNombre()+"\nApellido: "+getApellido()+"\nSaldo: $"+getSaldo());
    }
}

class CuentaSociedad extends Cuenta{
    private String nombreEmpresa;
    private String tipoEmpresa;

    public CuentaSociedad(int numeroCuenta, double saldo){
        super(numeroCuenta, saldo);
    }

    public CuentaSociedad(int numeroCuenta, double saldo, String nombreEmpresa, String tipoEmpresa){
        super(numeroCuenta, saldo);
        this.nombreEmpresa = nombreEmpresa;
        this.tipoEmpresa = tipoEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    @Override
    public void depositar(double cantidad) {
        setSaldo(getSaldo()+cantidad);
    }

    @Override
    public void retirar(double cantidad) {
        if (cantidad <= getSaldo()){
            setSaldo(getSaldo()-cantidad);
        } else {
            System.out.println("No se puede retirar más dinero del disponible");
        }
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Número de cuenta: "+getNumeroCuenta()+"\nNombre de la Empresa: "+getNombreEmpresa()+"\nTipo de empresa: "+getTipoEmpresa()+"\nSaldo: $"+getSaldo());
    }
}

class GestorCuentas{
    ArrayList<CuentaPersona> personas;
    ArrayList<CuentaSociedad> empresas;

    public GestorCuentas(){
        personas = new ArrayList<CuentaPersona>();
        empresas = new ArrayList<CuentaSociedad>();
    }

    public void agregarCuentaPersona(CuentaPersona persona){
        personas.add(persona);
    }

    public void agregarCuentaSociedad(CuentaSociedad empresa){
        empresas.add(empresa);
    }

    public void eliminarCuentaPersona(int numeroCuenta){
        for (int i = 0; i < personas.size(); i++){
            if (personas.get(i).getNumeroCuenta() == numeroCuenta){
                personas.remove(i);
                break;
            }
        }
    }

    public void eliminarCuentaSociedad(int numeroCuenta){
        for(int i = 0; i < empresas.size(); i++){
            if (empresas.get(i).getNumeroCuenta() == numeroCuenta){
                empresas.remove(i);
                break;
            }
        }
    }

    public void editarCuentaPersona(int numeroCuenta, double nuevoSaldo){
        for (CuentaPersona persona : personas){
            if (persona.getNumeroCuenta() == numeroCuenta){
                persona.setSaldo(nuevoSaldo);
            }
        }
    }

    public void editarCuentaSociedad(int numeroCuenta, double nuevoSaldo){
        for(CuentaSociedad empresa : empresas){
            if (empresa.getNumeroCuenta() == numeroCuenta){
                empresa.setSaldo(nuevoSaldo);
            }
        }
    }


    public void mostrarTodasLasCuentas(){
        System.out.println("Cuentas de personas: ");
        for (CuentaPersona persona : personas){
            persona.mostrarInformacion();
            System.out.println();
        }
        System.out.println("Cuentas de sociedades: ");
        for (CuentaSociedad empresa : empresas){
            empresa.mostrarInformacion();
            System.out.println();
        }

    }


}

class Main{

    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        GestorCuentas gestor = new GestorCuentas();
        int numerodeCuentaPersona = 1;
        int numeroDeCuentaEmpresa = 1;
        while(true){
            System.out.println("1. Crear y agregar cuentas de personas y sociedades\n2. Eliminar cuentas de personas y sociedades\n3. Editar el saldo de cuenta de personas y sociedades\n4. Mostrar la información de todas las cuentas\n5. Salir");
            System.out.println("Seleccione una opción: ");

            int opcion = scanner.nextInt();

            switch(opcion){
                case 1:
                    System.out.println("1. Cuenta de Persona\n2. Cuenta de Sociedad");
                    System.out.println("Elija una opción: ");
                    int cuenta = scanner.nextInt();
                    if(cuenta == 1){
                        System.out.println("Ingrese el nombre de la persona: ");
                        String nombrePersona = scanner.next();
                        System.out.println("Ingrese el apellido de la persona: ");
                        String apellidoPersona = scanner.next();
                        System.out.println("Ingrese el saldo: ");
                        double saldo = scanner.nextDouble();
                        CuentaPersona persona1 = new CuentaPersona(numerodeCuentaPersona, saldo, nombrePersona, apellidoPersona);
                        gestor.agregarCuentaPersona(persona1);
                        numerodeCuentaPersona++;
                    } else {
                        System.out.println("Ingrese el nombre de la empresa: ");
                        String nombreEmpersa = scanner.next();
                        System.out.println("Ingrese el tipo de empresa: ");
                        String tipoEmpresa = scanner.next();
                        System.out.println("Ingrese el saldo: ");
                        double saldo = scanner.nextDouble();
                        CuentaSociedad empresa1 = new CuentaSociedad(numeroDeCuentaEmpresa, saldo, nombreEmpersa, tipoEmpresa);
                        gestor.agregarCuentaSociedad(empresa1);
                        numeroDeCuentaEmpresa++;
                    }
                    break;
                case 2:
                    System.out.println("1. Cuenta de Persona\n2.Cuenta de Sociedad");
                    System.out.println("Elija una opción: ");
                    cuenta = scanner.nextInt();
                    if (cuenta == 1){
                        System.out.println("Ingrese el número de cuenta de persona a eliminar: ");
                        int cuentaAEliminar = scanner.nextInt();
                        gestor.eliminarCuentaPersona(cuentaAEliminar);
                    } else {
                        System.out.println("Ingrese el número de cuenta de empresa a eliminar: ");
                        int cuentaAEliminar = scanner.nextInt();
                        gestor.eliminarCuentaSociedad(cuentaAEliminar);
                    }
                    break;
                case 3:
                    System.out.println("1. Cuenta de Persona\n2. Cuenta de Sociedad");
                    System.out.println("Elija una opción: ");
                    cuenta = scanner.nextInt();
                    if(cuenta == 1){
                        System.out.println("Ingrese el número de cuenta de persona a editar: ");
                        int cuentaAEditar = scanner.nextInt();
                        System.out.println("Ingrese el nuevo saldo de la cuenta: ");
                        double nuevoSaldo = scanner.nextDouble();
                        gestor.editarCuentaPersona(cuentaAEditar, nuevoSaldo);
                    } else {
                        System.out.println("Ingrese el núermo de ceunta de sociedad a editar: ");
                        int cuentaAEditar = scanner.nextInt();
                        System.out.println("Ingrese el nuevo saldo de la cuenta: ");
                        double nuevoSaldo = scanner.nextDouble();
                        gestor.editarCuentaSociedad(cuentaAEditar, nuevoSaldo);
                    }
                    break;
                case 4:
                    gestor.mostrarTodasLasCuentas();
                    break;
                case 5:
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción inválida. Intente nuevamente");
            }

        }


    }
}