package Dao;

import Modelo.Detalles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Modelo.Conexion; // Asegúrate de importar la clase Conexion desde el paquete Modelo

public class DetallesDao {

    /**
     * Lista todos los detalles de todas las compras.
     * @return una lista de objetos Detalles.
     */
    public List<Detalles> lista() {
        List<Detalles> detalle = new ArrayList<>(); // La variable se llamaba 'detalle', no 'datos'.
        String sql = "SELECT * FROM Detalles";

        try (
            Connection con = Conexion.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                Detalles dt = new Detalles();
                dt.setid_detalle(rs.getInt("id_detalle")); // Es mejor usar el nombre de la columna.
                dt.setid_producto(rs.getInt("id_producto"));
                dt.setid_historial(rs.getInt("id_historial"));
                dt.setprecio_producto(rs.getDouble("precio_producto"));
                dt.setfecha(rs.getString("fecha"));
                dt.setcantidad(rs.getInt("cantidad"));
                detalle.add(dt); // Faltaba agregar el objeto a la lista.
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error de Consulta", JOptionPane.ERROR_MESSAGE);
        }
        return detalle; // El error estaba aquí, debe retornar la lista 'detalle'.
    }

    /**
     * Agrega un nuevo registro de detalle de compra a la base de datos.
     * @param dt El objeto Detalles con la información a guardar.
     * @return 1 si fue exitoso, 0 si falló.
     */
    public int agregarDetalle(Detalles dt) {
        // Asumiendo que id_detalle es autoincremental en tu BD y no se incluye en el INSERT.
        String sql = "INSERT INTO Detalles (id_producto, id_historial, precio_producto, fecha, cantidad) VALUES (?,?,?,?,?)";
        
        try (
            Connection con = Conexion.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setInt(1, dt.getid_producto());
            ps.setInt(2, dt.getid_historial());
            ps.setDouble(3, dt.getprecio_producto());
            ps.setString(4, dt.getfecha());
            ps.setInt(5, dt.getcantidad());
            
            return ps.executeUpdate();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error al agregar Detalle", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }
}