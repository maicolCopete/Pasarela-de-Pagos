/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy;

/**
 *
 * @author gfhgy
 */

public class PagoApplePay implements PagoStrategy {
    @Override
    public boolean pagar(double monto) {
        System.out.println("Procesando pago con APPLE PAY por un monto de: $" + monto);
        return true;
    }
}
