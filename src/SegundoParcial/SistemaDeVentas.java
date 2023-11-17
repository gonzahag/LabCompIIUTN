package SegundoParcial;



import java.sql.*;
import java.util.ArrayList;
import java.util.Date;


//2. Clase Vendedor y Producto
    class Vendedor{
        private int id;
        private String nombre;
        private String apellido;
        private String dni;
        private Date fecha_nacimiento;
        private Date fecha_contratacion;

        public Vendedor(int id, String nombre, String apellido, String dni, Date fecha_nacimiento, Date fecha_contratacion){

            this.id = id;
            this.nombre = nombre;
            this.apellido = apellido;
            this.dni = dni;
            this.fecha_nacimiento = fecha_nacimiento;
            this.fecha_contratacion = fecha_contratacion;

        }


        public Vendedor(String consultaBusqueda) {

            ResultSet vendedor = DBHelper.ejecutarConsultaConResultado(consultaBusqueda);
            if (vendedor != null) {
                try {
                    if (vendedor.next()) {
                        this.id = vendedor.getInt("vendedor_id");
                        this.nombre = vendedor.getString("nombre");
                        this.apellido = vendedor.getString("apellido");
                        this.dni = vendedor.getString("dni");
                        this.fecha_nacimiento = vendedor.getDate("fecha_nacimiento");
                        this.fecha_contratacion = vendedor.getDate("fecha_contratacion");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
        }
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getDni() {
            return dni;
        }

        public void setDni(String dni) {
            this.dni = dni;
        }

        public Date getFecha_nacimiento() {
            return fecha_nacimiento;
        }

        public void setFecha_nacimiento(Date fecha_nacimiento) {
            this.fecha_nacimiento = fecha_nacimiento;
        }

        public Date getFecha_contratacion() {
            return fecha_contratacion;
        }

        public void setFecha_contratacion(Date fecha_contratacion) {
            this.fecha_contratacion = fecha_contratacion;
        }


        @Override
        public String toString() {
            return "Vendedor{" +
                    "id=" + id +
                    ", nombre='" + nombre + '\'' +
                    ", apellido='" + apellido + '\'' +
                    ", dni='" + dni + '\'' +
                    ", fecha_nacimiento=" + fecha_nacimiento +
                    ", fecha_contratacion=" + fecha_contratacion +
                    '}';
        }
    }

    class Producto{

        private int id;
        private String nombre;
        private double precio_por_unidad;
        private int stock;


        public Producto(int id, String nombre, double precio_por_unidad, int stock){
            this.id = id;
            this.nombre = nombre;
            this.precio_por_unidad = precio_por_unidad;
            this.stock = stock;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public double getPrecio_por_unidad() {
            return precio_por_unidad;
        }

        public void setPrecio_por_unidad(double precio_por_unidad) {
            this.precio_por_unidad = precio_por_unidad;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }


        @Override
        public String toString() {
            return "Producto{" +
                    "id=" + id +
                    ", nombre='" + nombre + '\'' +
                    ", precio_por_unidad=" + precio_por_unidad +
                    ", stock=" + stock +
                    '}';
        }
    }



//1. Clase DBHelper
class DBHelper{
    public static final String URL = "jdbc:mysql://localhost:3306/ventas    ";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public void ejecutarConsulta(String consulta){
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


class Comerciales{
        private static ArrayList<Vendedor> vendedores;

        public Comerciales() {
            vendedores = new ArrayList<Vendedor>();
        }
        public static Vendedor obtenerVendedorPorId(int vendedorId){
            String consulta = "SELECT * FROM vendedores WHERE vendedor_id = " +vendedorId +"";
            ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);

            if (resultado != null) {
                try {
                    if (resultado.next()) {
                        int vendedorID = resultado.getInt("vendedor_id");
                        String nombre = resultado.getString("nombre");
                        String apellido = resultado.getString("apellido");
                        String dni = resultado.getString("dni");
                        java.sql.Date fechaNacimiento = resultado.getDate("fecha_nacimiento");
                        java.sql.Date fechaContratacion = resultado.getDate("fecha_contratacion");


                        return new Vendedor(vendedorID, nombre, apellido, dni, fechaNacimiento, fechaContratacion);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        public static ArrayList<Vendedor> listaDeVendedores(){
            String consulta = "SELECT * FROM vendedores";
            ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);

            if (resultado != null) {
                try {

                    while (resultado.next()) {
                        int vendedorID = resultado.getInt("vendedor_id");
                        String nombre = resultado.getString("nombre");
                        String apellido = resultado.getString("apellido");
                        String dni = resultado.getString("dni");
                        java.sql.Date fechaNacimiento = resultado.getDate("fecha_nacimiento");
                        java.sql.Date fechaContratacion = resultado.getDate("fecha_contratacion");

                        Vendedor vendedor = new Vendedor(vendedorID, nombre, apellido, dni, fechaNacimiento, fechaContratacion);
                        vendedores.add(vendedor);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return vendedores;
        }
    }



class Productos {

    public void generarInforme() {
        String consulta = "SELECT * FROM productos";
        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);
        double total = 0;
        if (resultado != null) {
            try {
                System.out.println("Informe de Productos en Stock:");
                System.out.printf("%-30s %-10s %-8s %-5s\n", "Producto", "Stock", "Precio", "Total");
                System.out.println("-----------------------------------------------------------------");

                while (resultado.next()) {
                    String nombreProducto = resultado.getString("nombre");
                    int stock = resultado.getInt("stock");
                    double precioUnitario = resultado.getDouble("precio_por_unidad");
                    double precioTotal = precioUnitario * stock;
                    total += precioTotal;
                    System.out.printf("%-30s %-10s %-8s %-5s\n", nombreProducto, stock, precioUnitario, precioTotal);

                }
                System.out.println("-----------------------------------------------------------------");
                System.out.printf("                               Total: %-13s", total);


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public static Producto obtenerProducto(int productoId){
        String consulta = "SELECT * FROM productos WHERE producto_id = " +productoId +"";
        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);

        if (resultado != null) {
            try {
                if(resultado.next()) {
                    int productoID = resultado.getInt("producto_id");
                    String nombre = resultado.getString("nombre");
                    double precioUnidad = resultado.getDouble("precio_por_unidad");
                    int stock = resultado.getInt("stock");
                    return new Producto(productoID, nombre, precioUnidad, stock);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static Producto obtenerProductoMasVendido(){
        String consulta = "SELECT producto_id, SUM(cantidad_vendida) as total_vendido FROM ventas GROUP BY producto_id ORDER BY total_vendido DESC LIMIT 1";
        ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);
        if (resultado != null) {
            try {
                if (resultado.next()) {
                    int productoID = resultado.getInt("producto_id");
                    return obtenerProducto(productoID);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    }



class SistemaDeVentas{

        public static void main(String args[]){
            Productos productos = new Productos();
            Comerciales comerciales = new Comerciales();

            /* 3. Vendedor con consulta en constructor
            Vendedor vendedorBuscado = new Vendedor("SELECT * FROM `vendedores` WHERE `vendedor_id` = 3");
            System.out.println(vendedorBuscado);
             */


            /*  4. Obtener los datos de un vendedor
            System.out.println(comerciales.obtenerVendedorPorId(3));
            */

            /* 5. Generación de informe de productos en stock
            productos.generarInforme();
             */

            /* 6. Obtener productos por ID
            System.out.println(productos.obtenerProducto(12));
             */

            /* 7. Obtener producto más vendido
            System.out.println(productos.obtenerProductoMasVendido());
            */

            /*8. Listado de vendedores
            System.out.println("\n" + comerciales.listaDeVendedores());
            */


        }

}











