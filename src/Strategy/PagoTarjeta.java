package Strategy;

// Asegúrate que esta clase implemente la interfaz
public class PagoTarjeta implements PagoStrategy {
    
    @Override
    public boolean pagar(double monto) {
       
        System.out.println("Procesando pago con TARJETA por un monto de: $" + monto);

        return true; 
    }
}