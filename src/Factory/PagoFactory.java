package Factory;

import Strategy.PagoApplePay;
import Strategy.PagoBitcoin;
import Strategy.PagoConsignacion;
import Strategy.PagoEfectivo;
import Strategy.PagoGooglePay;
import Strategy.PagoPaypal;
import Strategy.PagoStrategy;
import Strategy.PagoTarjeta; // Importar la clase
import Strategy.PagoTransferencia;
import java.util.HashMap;
import java.util.Map;

public class PagoFactory {
    // Hacemos el mapa estático para que se inicialice una sola vez
    private final static Map<TipoDePago, PagoStrategy> ESTRATEGIAS_PAGO = new HashMap<>();

    // Bloque estático para inicializar el mapa
    static {
        ESTRATEGIAS_PAGO.put(TipoDePago.EFECTIVO, new PagoEfectivo());
        ESTRATEGIAS_PAGO.put(TipoDePago.TARJETA_CREDITO, new PagoTarjeta());
        ESTRATEGIAS_PAGO.put(TipoDePago.TARJETA_DEBITO, new PagoTarjeta()); // Reutilizamos la misma estrategia
        ESTRATEGIAS_PAGO.put(TipoDePago.PAYPAL, new PagoPaypal());
        ESTRATEGIAS_PAGO.put(TipoDePago.BITCOIN, new PagoBitcoin());
        ESTRATEGIAS_PAGO.put(TipoDePago.TRANSFERENCIAS, new PagoTransferencia()); 
        ESTRATEGIAS_PAGO.put(TipoDePago.CONSIGNACION, new PagoConsignacion());
    ESTRATEGIAS_PAGO.put(TipoDePago.APPLE_PAY, new PagoApplePay());
    ESTRATEGIAS_PAGO.put(TipoDePago.GOOGLE_PAY, new PagoGooglePay());
        // Agrega aquí las otras estrategias...
        // ESTRATEGIAS_PAGO.put(TipoDePago.TRANSFERENCIAS, new PagoTransferencia());
    }

    public static PagoStrategy obtenerPago(TipoDePago tipoDePago) {
        if (tipoDePago == null || !ESTRATEGIAS_PAGO.containsKey(tipoDePago)) {
            // Manejo de un caso no soportado
            throw new IllegalArgumentException("Método de pago no soportado: " + tipoDePago);
        }
        return ESTRATEGIAS_PAGO.get(tipoDePago);
    }
}