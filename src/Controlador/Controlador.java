package Controlador;
import Dao.CuentaUsuarioDao;
import Dao.UsuarioDao;
import Modelo.Usuario;
import Dao.DetallesDao;
import Dao.HistorialPagoDao;
import Dao.ProductoDao;
import Modelo.Detalles;
import Modelo.Historial_pago;
import Modelo.Producto;
import Factory.PagoFactory;
import Factory.TipoDePago;
import Strategy.PagoStrategy;
import View.Interfaz;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;




public class Controlador implements ActionListener {

   
CuentaUsuarioDao cuentaDao = new CuentaUsuarioDao();
    ProductoDao productoDao = new ProductoDao();
    HistorialPagoDao historialDao = new HistorialPagoDao();
    DetallesDao detallesDao = new DetallesDao();
    UsuarioDao usuarioDao=new UsuarioDao();
    
    
    Interfaz vista;
    private Usuario usuarioLogueado;
    private List<Producto> listaDeProductos;
     private List<Usuario> listaDeUsuarios;
    
    private double totalPagar = 0.0;

   public Controlador(Interfaz v, Usuario usuario) {
    this.vista = v;
    this.usuarioLogueado = usuario; // Guardamos el usuario
    
    this.vista.btnAgregar.addActionListener(this);
    this.vista.btnConfirmar.addActionListener(this);
    this.vista.comboPago.addActionListener(this);

    listarProductosEnCombo();
   cargarTablaHistorial();
    cargarU(); 
}

    @Override
    public void actionPerformed(ActionEvent e) {
      
        if (e.getSource() == vista.btnAgregar) {
            agregarProductoATabla();
        } 
        else if (e.getSource() == vista.btnConfirmar) {
            procesarCompra();
        } 
        else if (e.getSource() == vista.comboPago) {
            String itemSeleccionado = (String) vista.comboPago.getSelectedItem();
            CardLayout cl = (CardLayout)(vista.panelPago.getLayout());
            cl.show(vista.panelPago, itemSeleccionado);
        }
    }
    
   private void cargarU() {
   // vista.comboUsuario.removeAllItems(); 
    vista.comboUsuario.addItem(this.usuarioLogueado); 
   
}
   
   private void cargarTablaHistorial() {
   
    DefaultTableModel modelo = (DefaultTableModel) vista.tablaHistorial.getModel();
    modelo.setRowCount(0);

    List<Historial_pago> lista = historialDao.lista();

    for (Historial_pago hp : lista) {
        Object[] fila = new Object[5];
        fila[0] = hp.getId_historial();
        fila[1] = hp.getDocumento();
        fila[2] = hp.getFecha();
        fila[3] = hp.getMonto();
        fila[4] = hp.getId_metodo();
        modelo.addRow(fila);
    }
}
    private void listarProductosEnCombo() {
   
        listaDeProductos = productoDao.lista();
        vista.comboProducto.removeAllItems();
        for (Producto p : listaDeProductos) {
            vista.comboProducto.addItem(p.getNombreP());
        }
    }

    private void agregarProductoATabla() {
        
        String cantidadTexto = vista.txtCantidad.getText();
        if (cantidadTexto.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor, ingrese una cantidad numérica válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int cantidad = Integer.parseInt(cantidadTexto);
        if (cantidad <= 0) {
            JOptionPane.showMessageDialog(vista, "La cantidad debe ser mayor a cero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int indiceSeleccionado = vista.comboProducto.getSelectedIndex();
        if (indiceSeleccionado < 0) {
            JOptionPane.showMessageDialog(vista, "Por favor, seleccione un producto.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Producto productoSeleccionado = listaDeProductos.get(indiceSeleccionado);
        float precio = productoSeleccionado.getPrecioP();
        double subtotal = cantidad * precio;
        DefaultTableModel modelo = (DefaultTableModel) vista.tablaProductos.getModel();
        Object[] fila = new Object[]{productoSeleccionado.getIdP(), productoSeleccionado.getNombreP(), cantidad, precio, subtotal};
        modelo.addRow(fila);
        actualizarTotal();
        vista.txtCantidad.setText("");
    }
    
//     private void tablaU() {
//         return;
//     }

    private void actualizarTotal() {
        
       
        totalPagar = 0.0;
        DefaultTableModel modelo = (DefaultTableModel) vista.tablaProductos.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            totalPagar += (double) modelo.getValueAt(i, 4);
        }
        System.out.println("Total a pagar actualizado: " + totalPagar);
    }
    
   private void procesarCompra() {
    if (vista.tablaProductos.getRowCount() == 0) {
        JOptionPane.showMessageDialog(vista, "No hay productos en el carrito.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    TipoDePago tipoDePago = getTipoDePagoDesdeString(vista.comboPago.getSelectedItem().toString());
    if (tipoDePago == null) {
        JOptionPane.showMessageDialog(vista, "Por favor, seleccione un método de pago válido.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    

    Usuario usuarioLogueado = (Usuario) vista.comboUsuario.getSelectedItem();
    int documento = usuarioLogueado.getDocumento();
    int idMetodoPago = tipoDePago.getId();
    boolean pagoValido = false; 
    if (tipoDePago == TipoDePago.EFECTIVO) {
        String montoRecibidoStr = vista.txtMontoRecibido.getText();
        if (montoRecibidoStr.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor, ingrese un monto recibido válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double montoRecibido = Double.parseDouble(montoRecibidoStr);

        if (montoRecibido < totalPagar) {
            JOptionPane.showMessageDialog(vista, "El monto recibido es insuficiente.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // No se procede
        }
        
        double cambio = montoRecibido - totalPagar;
        JOptionPane.showMessageDialog(vista, "Pago procesado. Su cambio es: $" + String.format("%.2f", cambio));
        pagoValido = true;

    } else {
        double saldoActual = cuentaDao.obtenerSaldo(documento, idMetodoPago);
        
        if (saldoActual < totalPagar) {
            JOptionPane.showMessageDialog(vista, "Saldo insuficiente. Su saldo es: $" + saldoActual, "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }
        
        double nuevoSaldo = saldoActual - totalPagar;
        boolean actualizacionExitosa = cuentaDao.actualizarSaldo(documento, idMetodoPago, nuevoSaldo);
        
        if (actualizacionExitosa) {
            JOptionPane.showMessageDialog(vista, "Pago aprobado. Su nuevo saldo es: $" + nuevoSaldo);
            pagoValido = true;
        } else {
            JOptionPane.showMessageDialog(vista, "Hubo un error al actualizar su saldo.", "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    if (pagoValido) {
        PagoStrategy estrategiaDePago = PagoFactory.obtenerPago(tipoDePago);
        boolean pagoExitoso = estrategiaDePago.pagar(totalPagar); 

        if (pagoExitoso) {
            guardarCompraEnBD(); 
            
           
            DefaultTableModel modelo = (DefaultTableModel) vista.tablaProductos.getModel();
            modelo.setRowCount(0);
            actualizarTotal();
            vista.txtMontoRecibido.setText("");
        }
    }
}
    
   
  private void guardarCompraEnBD() {
    Historial_pago historial = new Historial_pago();

    historial.setDocumento(this.usuarioLogueado.getDocumento());
    
    historial.setMonto((float) totalPagar);
    
    TipoDePago tipoDePago = getTipoDePagoDesdeString(vista.comboPago.getSelectedItem().toString());
    historial.setId_metodo(tipoDePago.getId());

    int idHistorialGenerado = historialDao.agregar(historial);

    if (idHistorialGenerado == 0) {
        JOptionPane.showMessageDialog(vista, "Error: No se pudo guardar el historial de la compra. Rwvisar db");
        return;
    }

    DefaultTableModel modelo = (DefaultTableModel) vista.tablaProductos.getModel();
    int detallesGuardados = 0;
    for (int i = 0; i < modelo.getRowCount(); i++) {
        Detalles detalle = new Detalles();
        detalle.setid_historial(idHistorialGenerado);
        detalle.setid_producto((int) modelo.getValueAt(i, 0));
        detalle.setcantidad((int) modelo.getValueAt(i, 2));
        detalle.setprecio_producto((double) modelo.getValueAt(i, 4)); // Ajustado índice a la columna subtotal
        detalle.setfecha(LocalDate.now().toString());

        if (detallesDao.agregarDetalle(detalle) > 0) {
            detallesGuardados++;
        }
    }
    
    JOptionPane.showMessageDialog(vista,"Compra registrada exitosamente");
}
    
    private TipoDePago getTipoDePagoDesdeString(String metodo) {
        switch (metodo) {
            case "Efectivo": return TipoDePago.EFECTIVO;
            case "Tarjeta Credito": return TipoDePago.TARJETA_CREDITO;
            case "Tarjeta Debito": return TipoDePago.TARJETA_DEBITO;
            case "PayPal": return TipoDePago.PAYPAL;
            case "Bitcoin": return TipoDePago.BITCOIN;
            case "Transferencia": return TipoDePago.TRANSFERENCIAS;
            case "Consignacion": return TipoDePago.CONSIGNACION;
            case "Apple pay": return TipoDePago.APPLE_PAY;
            case "Google Pay": return TipoDePago.GOOGLE_PAY;
            default: return null;
        }
    }
}