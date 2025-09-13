package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Modelo.Historial_pago;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Modelo.Conexion; // Asegúrate de importar la clase Conexion desde el paquete Modelo

public class HistorialPagoDao {

    public List<Historial_pago> lista() {
    List<Historial_pago> datos = new ArrayList<>();

    String sql = "SELECT id_historial, Documento, fecha, monto, Id_Metodo FROM Historial";

    try (Connection con = Conexion.getInstance().getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery();
    ) {
        while (rs.next()) {
            Historial_pago hp = new Historial_pago();
         
            hp.setId_historial(rs.getInt("id_historial"));
            hp.setDocumento(rs.getInt("Documento"));        
            hp.setFecha(rs.getString("fecha")); // La BD devuelve la fecha como texto
            hp.setMonto(rs.getFloat("monto"));
            hp.setId_metodo(rs.getInt("Id_Metodo"));
            datos.add(hp);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.toString(), "Error de Consulta", JOptionPane.ERROR_MESSAGE);
    }
    return datos;
}

   
    public int agregar(Historial_pago hp) {
    String sql = "INSERT INTO Historial (Documento, Id_Metodo, monto) VALUES (?,?,?)";
    
    try (
        Connection con = Conexion.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    ) {
      
        ps.setInt(1, hp.getDocumento());
        ps.setInt(2, hp.getId_metodo());
        ps.setFloat(3, hp.getMonto());
        
        ps.executeUpdate();

        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new Exception("No se pudo obtener el ID del historial.");
            }
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.toString(), "Error al agregar Historial", JOptionPane.ERROR_MESSAGE);
        return 0;
    }
}
    
}