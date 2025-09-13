package Strategy;

public class PagoEfectivo implements PagoStrategy {
    private double saldo;
    
    
    @Override
    public boolean pagar(double monto) {
        System.out.println("Procesando pago en efectivo de: " + monto);
        return true;
    }
}

