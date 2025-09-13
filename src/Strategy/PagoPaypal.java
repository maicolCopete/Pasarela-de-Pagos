package Strategy;

public class PagoPaypal implements PagoStrategy {
    
    @Override
    public boolean pagar(double monto) {
        System.out.println("Procesando pago con PAYPAL por un monto de: $" + monto);
        return true;
    }
}