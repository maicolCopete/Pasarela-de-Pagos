package Factory;

public enum TipoDePago {

    EFECTIVO(1),
    PAYPAL(2),
    TARJETA_DEBITO(3),
    TARJETA_CREDITO(4),
    TRANSFERENCIAS(5),
    BITCOIN(6),
    CONSIGNACION(7),
    APPLE_PAY(8),
    GOOGLE_PAY(9);

    private final int id;


    private TipoDePago(int id) {
        this.id = id;
    }

   
    public int getId() {
        return id;
    }
}