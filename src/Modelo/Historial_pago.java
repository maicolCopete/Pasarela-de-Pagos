package Modelo;

public class Historial_pago {
    
    private int id_historial; 

    
    private int documento;
    private int id_metodo;
    private String fecha;
    private float monto;
    
    
    public Historial_pago(int documento,  int id_metodo, String fecha,  float monto) {
        this.documento = documento;
        this.id_metodo = id_metodo;
        this.fecha = fecha;
        this.monto = monto;
    }
    
    public Historial_pago() {}
    
    public int getId_historial() {
        return id_historial;
    }

    public void setId_historial(int id_historial) {
        this.id_historial = id_historial;
    }
    public int getDocumento() {
        return documento;
    }
    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public int getId_metodo() {
        return id_metodo;
    }

    public void setId_metodo(int id_metodo) {
        this.id_metodo = id_metodo;
    }
    
 
   
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public float getMonto() {
        return monto;
    }
    public void setMonto(float monto) {
        this.monto = monto;
    }
}
