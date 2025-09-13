package Modelo;

public class Producto {
    private int id_producto;
    private String nombre_producto;
    private float precio_producto;
    private int cant_producto;
    
    public Producto(int id_producto, String nombre_producto, float precio_producto, int cant_producto) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.precio_producto = precio_producto;
        this.cant_producto = cant_producto;
    }
    
    public Producto() {}
    
    public void setIdP(int id_producto) {
        this.id_producto = id_producto;
    }
    public int getIdP() {
        return this.id_producto;
    }
    
    public void setNombreP(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }
    public String getNombreP() {
        return this.nombre_producto;
    }
    
    public void setPrecioP(float precio_producto) {
        this.precio_producto = precio_producto;
    }
    public float getPrecioP() {
        return this.precio_producto;
    }
    
    public void setCantP(int cant_producto) {
        this.cant_producto = cant_producto;
    }
    public int getCantP() {
        return this.cant_producto;
    }
}
