package pasarela;

import java.util.HashMap;
import java.util.Map;

public class PagoFactory {
    
    private final static Map<TipoDePago, Pagos> pagos = new HashMap<>(){{
        put(TipoDePago.PAYPAL, new PagoPayPal());
        put(TipoDePago.EFECTIVO, new PagoEfectivo());
    
    }}; 
    
    public Pagos obtenerPago(TipoDePago tipoDePago) {
        return pagos.get(tipoDePago);
    }
}
