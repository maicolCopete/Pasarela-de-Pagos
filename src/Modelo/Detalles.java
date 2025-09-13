package Modelo;

public class Detalles {
    private int id_detalle;
    private int id_producto;
    private int id_historial;
    private double precio_producto;
    private String fecha;
    private int cantidad;
   
    
    public Detalles(int id_detalle, int id_producto, int id_historial, double precio_producto, String fecha,int cantidad){
        this.id_detalle = id_detalle;
        this.id_producto = id_producto;
        this.id_historial = id_historial;
        this.precio_producto = precio_producto;
        this.fecha = fecha;
        this.cantidad = cantidad;
    
    }
    
        public Detalles(){}
        
    public void setid_detalle( int id_detalle){
        this.id_detalle = id_detalle;
    }
    
    public int getid_detalle(){
        return id_detalle;
    }
    
    public void setid_producto(int id_producto){
        this.id_producto = id_producto;
    }
    
    public int getid_producto(){
        return id_producto;
    }
    
    public void setid_historial(int id_historial){
        this.id_historial=id_historial;
    }
    
    public int getid_historial(){
        return id_historial;
    }
    
    public void setprecio_producto( double precio_producto){
        this.precio_producto= precio_producto;
    }
    
    public double getprecio_producto(){
        return precio_producto;
    }
    
    public void setfecha(String fecha){
        this.fecha=fecha;
    }
    
    public String getfecha(){
        return fecha;
    }
    
    public void setcantidad(int cantidad){
        this.cantidad=cantidad;
    }
    
    public int getcantidad(){
        return cantidad;
    }
    
    
    
    
    
    
    
}
