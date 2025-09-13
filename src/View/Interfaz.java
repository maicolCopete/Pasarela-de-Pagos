package View;

import Factory.TipoDePago;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import Modelo.Usuario;
import javax.swing.border.EmptyBorder;

public class Interfaz extends JFrame {

    public JTabbedPane pestañas;
    public JTable tablaProductos, tablaHistorial;
    public DefaultTableModel modeloProductos, modeloHistorial;


    public JComboBox<String> comboProducto;
   public JComboBox<Usuario> comboUsuario; 
   public JTextField txtMontoRecibido; 
    public JTextField txtCantidad,tarjeta,cvv;
    public JButton btnAgregar;
    public JButton btnConfirmar;
    public JComboBox<String> comboPago;

   
    public JPanel panelPago;
    public CardLayout cardLayout;

    public Interfaz() {
        setTitle("Compras");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        pestañas = new JTabbedPane();
        pestañas.addTab("Compra y Pago", crearPanelCompra());
        pestañas.addTab("Historial", crearPanelHistorial());

        add(pestañas);
        setVisible(true);
    }

    private JPanel crearPanelCompra() {
        JPanel panel = new JPanel(new BorderLayout());

        
        JPanel panelSuperior = new JPanel();
        comboUsuario = new JComboBox<>();
        comboProducto = new JComboBox<>(); 
        txtCantidad = new JTextField(5);
    
        btnAgregar = new JButton("Agregar");

        panelSuperior.add(new JLabel("Usuario:"));
        panelSuperior.add(comboUsuario);
        panelSuperior.add(new JLabel("Producto:"));
        panelSuperior.add(comboProducto);
        panelSuperior.add(new JLabel("Cantidad:"));
        panelSuperior.add(txtCantidad);
        panelSuperior.add(btnAgregar);

    
        String[] columnas = {"ID", "Producto", "Cantidad", "Precio Unit.", "Subtotal"};
        modeloProductos = new DefaultTableModel(columnas, 0);
        tablaProductos = new JTable(modeloProductos);
        JScrollPane scrollProductos = new JScrollPane(tablaProductos);

       
        JPanel panelInferior = new JPanel(new BorderLayout());

        String[] metodos = {"Seleccione...", "Efectivo", "Tarjeta Debito", "PayPal", "Tarjeta Credito", "Transferencia", "Consignacion", "Bitcoin", "Apple pay", "Google Pay"};
        comboPago = new JComboBox<>(metodos);

        cardLayout = new CardLayout();
        panelPago = new JPanel(cardLayout);

 
        panelPago.add(new JPanel(), "Seleccione...");
        panelPago.add(crearPagoEfectivo(), "Efectivo");
        panelPago.add(crearPagoTarjetaC(), "Tarjeta Credito");
        panelPago.add(crearPagoTarjetaD(), "Tarjeta Debito");
        panelPago.add(bitcoin(), "Bitcoin");
        panelPago.add(crearGooglePay(), "Google Pay");
        panelPago.add(CreaAPPLE_PAY(), "Apple pay");
        panelPago.add(cCONSIGNACION(), "Consignacion");
        panelPago.add(crearPagoPaypal(), "PayPal");
        panelPago.add(crearTransferencia(), "Transferencia");

        btnConfirmar = new JButton("Confirmar compra");

        panelInferior.add(comboPago, BorderLayout.NORTH);
        panelInferior.add(panelPago, BorderLayout.CENTER);
        panelInferior.add(btnConfirmar, BorderLayout.SOUTH);

        
        panel.add(panelSuperior, BorderLayout.NORTH);
        panel.add(scrollProductos, BorderLayout.CENTER);
        panel.add(panelInferior, BorderLayout.SOUTH);

        return panel;
    }


    private JPanel crearPagoEfectivo() {
    JPanel p = new JPanel(new GridLayout(2, 2, 5, 5));
    p.add(new JLabel("Monto Recibido:")); 
    txtMontoRecibido = new JTextField(10); 
    p.add(txtMontoRecibido);
    return p;
}

    private JPanel crearPagoTarjetaC() {
        JPanel p = new JPanel(new GridLayout(2, 2, 5, 5));
        p.add(new JLabel("Número Tarjeta:"));
        tarjeta=new JTextField(5);
        p.add(tarjeta);
        p.add(new JLabel("Expiración:"));
        p.add(new JTextField(10));
        p.add(new JLabel("CVV:"));
       cvv=new JTextField(3);
        p.add(cvv);
        p.add(new JLabel("Monto:"));
        p.add(new JTextField(5));
        return p;
    }
    

    private JPanel crearPagoPaypal() {
        JPanel p = new JPanel(new GridLayout(2, 2, 5, 5));
        p.add(new JLabel("Correo Asociado:"));
        p.add(new JTextField(10));
        p.add(new JLabel("Monto"));
        p.add(new JPasswordField(10));
        p.add(new JLabel("Fecha"));
        p.add(new JPasswordField(10));
        return p;
    }

    
    
   
    private JPanel crearTransferencia() {
        JPanel p = new JPanel(new GridLayout(2, 2, 5, 5));
        p.add(new JLabel("Banco Origen"));
        p.add(new JTextField(10));
        p.add(new JLabel("Banco Destino"));
        p.add(new JTextField(10));
        p.add(new JLabel("Monto"));
        p.add(new JPasswordField(10));
        p.add(new JLabel("Nombre Usuario"));
        p.add(new JPasswordField(10));
        p.add(new JLabel("Fecha"));
        p.add(new JPasswordField(10));
        return p;
    }

    private JPanel crearPagoTarjetaD() {
        JPanel p = new JPanel(new GridLayout(3, 2, 5, 5));
        p.add(new JLabel("Número Tarjeta:"));
        p.add(new JTextField(10));
        p.add(new JLabel("Expiración:"));
        p.add(new JTextField(10));
        p.add(new JLabel("CVV:"));
        p.add(new JTextField(5));
        p.add(new JLabel("Cuotas:"));
        p.add(new JTextField(5));
        p.add(new JLabel("Monto:"));
        p.add(new JTextField(5));
        return p;
    }

    private JPanel crearGooglePay() {
        JPanel p = new JPanel(new GridLayout(3, 2, 5, 5));
        p.add(new JLabel("Nombre:"));
        p.add(new JTextField(10));
        p.add(new JLabel("Numero Tarjeta:"));
        p.add(new JTextField(10));
        p.add(new JLabel("Monto:"));
        p.add(new JTextField(5));
        p.add(new JLabel("Fecha Pago"));
        p.add(new JTextField(5));
        p.add(new JLabel("Monto:"));
        p.add(new JTextField(5));
        return p;

    }

    private JPanel CreaAPPLE_PAY() {
        JPanel p = new JPanel(new GridLayout(2, 2, 5, 5));
        p.add(new JLabel("Nombre:"));
        p.add(new JTextField(10));
        p.add(new JLabel("Numero Tarjeta:"));
        p.add(new JTextField(10));
        p.add(new JLabel("Monto:"));
        p.add(new JTextField(5));
        p.add(new JLabel("Fecha Pago"));
        p.add(new JTextField(5));
        return p;
    }

    private JPanel cCONSIGNACION() {
        JPanel p = new JPanel(new GridLayout(2, 3, 5, 5));
        p.add(new JLabel("Nombre Depositante:"));
        p.add(new JTextField(10));
        p.add(new JLabel("Banco Destino"));
        p.add(new JTextField(10));
        p.add(new JLabel("Monto:"));
        p.add(new JTextField(5));
        p.add(new JLabel("Fecha Pago"));
        p.add(new JTextField(5));
        p.add(new JLabel("Monto:"));
        p.add(new JTextField(5));
        return p;
    }

    private JPanel bitcoin() {
        JPanel p = new JPanel(new GridLayout(2, 2, 5, 5));
        p.add(new JLabel("Direccion Wallet:"));
        p.add(new JTextField(10));
        p.add(new JLabel("Direccion Destino"));
        p.add(new JTextField(10));
        p.add(new JLabel("Monto:"));
        p.add(new JTextField(5));
        p.add(new JLabel("Fecha Pago"));
        p.add(new JTextField(5));
        return p;
    }

    private JPanel crearPanelHistorial() {
    JPanel panel = new JPanel(new BorderLayout(10, 10));
    panel.setBorder(new EmptyBorder(10, 10, 10, 10));
    String[] columnas = {"ID Transacción", "Documento Cliente", "Fecha", "Monto Total", "ID Método"}; 
    modeloHistorial = new DefaultTableModel(columnas, 0);
    tablaHistorial = new JTable(modeloHistorial);
    panel.add(new JScrollPane(tablaHistorial), BorderLayout.CENTER);
    return panel;
}
    
 



public boolean CAmpos(TipoDePago tipoDePago) {
    if (tipoDePago == TipoDePago.TARJETA_CREDITO || tipoDePago == TipoDePago.TARJETA_DEBITO) {
        String numero = this.tarjeta.getText().trim();
        String cvv=this.cvv.getText().trim();
        if (numero.isEmpty()||cvv.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El número de la tarjeta y el CVV no pueden estar vacíos.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (numero.length() != 16) {
            JOptionPane.showMessageDialog(this, "El número de la tarjeta debe tener 16 dígitos.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (cvv.length() != 3) {
            JOptionPane.showMessageDialog(this, "El CVV debe tener 3 dígitos.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    
    
    if (tipoDePago == TipoDePago.EFECTIVO) {
        String montoRecibidoStr = this.txtMontoRecibido.getText().trim();
        if (montoRecibidoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un monto recibido válido.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

   

    return true; 
}
    
}