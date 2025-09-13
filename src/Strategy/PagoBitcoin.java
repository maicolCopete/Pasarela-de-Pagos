package Strategy;

public class PagoBitcoin implements PagoStrategy {
    
    @Override
    public boolean pagar(double monto) {
        System.out.println("Procesando pago con BITCOIN por un monto de: $" + monto);
        return true;
    }
}