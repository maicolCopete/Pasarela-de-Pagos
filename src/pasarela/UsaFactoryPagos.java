package pasarela;

public class UsaFactoryPagos {

    public static void main(String[] args) {
        
        PagoFactory pagoFactory = new PagoFactory();
        Pagos pagos = pagoFactory.obtenerPago(TipoDePago.PAYPAL);
        
        pagos.crearPago();
        
        
    }
    
}
