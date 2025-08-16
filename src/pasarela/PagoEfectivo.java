package pasarela;

public class PagoEfectivo implements Pagos{
    
    @Override
    public void crearPago() {
        System.out.println("Se ha procesado un pago en efectivo");
    }
    
}
