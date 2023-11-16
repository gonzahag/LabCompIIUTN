package BaseDeDatos;

import java.sql.*;


abstract class Persona{
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad){
        this.nombre = nombre;
        this.edad = edad;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}

class Paciente extends Persona{
    private String historialMedico;
    private int doctorCabecera;
    private Date fechaIngreso;

    public Paciente(String nombre, int edad, String historialMedico, int doctorCabecera, Date fechaIngreso){
        super(nombre, edad);
        this.historialMedico = historialMedico;
        this.doctorCabecera = doctorCabecera;
        this.fechaIngreso = fechaIngreso;
    }

    public String getHistorialMedico() {
        return historialMedico;
    }

    public void setHistorialMedico(String historialMedico) {
        this.historialMedico = historialMedico;
    }

    public int getDoctorCabecera() {
        return doctorCabecera;
    }

    public void setDoctorCabecera(int doctorCabecera) {
        this.doctorCabecera = doctorCabecera;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }



}

class Doctor extends Persona{
    private String especialidad;

    public Doctor(String nombre, int edad, String especialidad){
        super(nombre, edad);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}


class Hospital {

    public void agregarPaciente(Paciente paciente){
        String consulta = "INSERT INTO pacientes (nombre, edad, historial_medico, doctor, fecha_ingreso) VALUES ('" + paciente.getNombre() + "', " + paciente.getEdad() + ", '" + paciente.getHistorialMedico() + "', " + paciente.getDoctorCabecera() + ", '" + paciente.getFechaIngreso() + "')";
        DBHelper.ejecutarConsulta(consulta);
    }

    public void eliminarPaciente(String nombre){
        String consulta = "DELETE FROM pacientes WHERE nombre = '" + nombre + "'";
        DBHelper.ejecutarConsulta(consulta);
    }

    public void asignarDoctorCabecera(String nombreDoctor, String nombrePaciente) {
        String consulta = "UPDATE pacientes SET doctor = (SELECT id FROM doctores WHERE nombre = '"+nombreDoctor+"') WHERE nombre = '"+nombrePaciente+"'";
        DBHelper.ejecutarConsulta(consulta);

    }
    public void listarPacientes() {
        String consulta = "SELECT * FROM pacientes";
        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);

        if (resultado != null) {
            try {
                System.out.println("Lista de Pacientes:");
                System.out.printf("%-10s %-15s %-5s %-20s %-12s %-10s\n", "ID", "Nombre", "Edad", "Historial Médico", "Fecha Ingreso", "Doctor");

                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    String nombre = resultado.getString("nombre");
                    int edad = resultado.getInt("edad");
                    String historialMedico = resultado.getString("historial_medico");
                    Date fechaIngreso = resultado.getDate("fecha_ingreso");
                    int idDoctor = resultado.getInt("doctor");

                    System.out.printf("%-10d %-15s %-5d %-20s %-12s %-10d\n", id, nombre, edad, historialMedico, fechaIngreso, idDoctor);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void listarPacientesEntreDosFechas(Date fechaDesde, Date fechaHasta){
        String consulta = "SELECT * FROM pacientes WHERE fecha_ingreso BETWEEN '"+fechaDesde+"' AND '"+fechaHasta+"';\n";
        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);

        if (resultado != null){
            try{
                System.out.println("Lista de pacientes:");
                System.out.printf("%-10s %-15s %-5s %-20s %-12s %-10s\n", "ID", "Nombre", "Edad", "Historial Médico", "Fecha Ingreso", "Doctor");

                while(resultado.next()){
                    int id = resultado.getInt("id");
                    String nombre = resultado.getString("nombre");
                    int edad = resultado.getInt("edad");
                    String historialMedico = resultado.getString("historial_medico");
                    Date fechaIngreso = resultado.getDate("fecha_ingreso");
                    int idDoctor = resultado.getInt("doctor");

                    System.out.printf("%-10d %-15s %-5d %-20s %-12s %-10d\n", id, nombre, edad, historialMedico, fechaIngreso, idDoctor);
                }

            } catch (SQLException e){
                e.printStackTrace();
            }
        }


    }

}

class DBHelper{
    public static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public static void ejecutarConsulta(String consulta){
        try{
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);


            try (PreparedStatement statement = connection.prepareStatement(consulta)){
                statement.executeUpdate();
            }

            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static ResultSet ejecutarConsultaConResultado(String consulta) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            PreparedStatement statement = connection.prepareStatement(consulta);

            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

class HospitalBaseDatos{
    public static void main(String args[]){

        Hospital hospital = new Hospital();
        Date fechaActual = new Date(2023 - 1900, 11 - 1, 15);
        Paciente paciente = new Paciente("Perez", 25, "Ninguno", 1, fechaActual);
        Paciente paciente2 = new Paciente("Carlitos", 25, "Ninguno", 1, fechaActual);
        //hospital.agregarPaciente(paciente);
        //hospital.agregarPaciente(paciente2);

        hospital.eliminarPaciente("Perez");
        hospital.eliminarPaciente("Carlitos");


        hospital.asignarDoctorCabecera("Doctor1", "Carlitos");


        hospital.listarPacientes();

        //listar pacientes entre dos fechas
        Date fechaDesde = new Date(2023 - 1900, 1 - 1, 1);
        Date fechaHasta = new Date(2023 - 1900, 1 - 1, 10);
        hospital.listarPacientesEntreDosFechas(fechaDesde, fechaHasta);

    }
}
