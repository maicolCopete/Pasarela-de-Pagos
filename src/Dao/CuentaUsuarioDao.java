/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

/**
 *
 * @author gfhgy
 */

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class CuentaUsuarioDao {

    
    public double obtenerSaldo(int documento, int idMetodoPago) {
        double saldo = 0.0;
        String sql = "SELECT saldo FROM cuenta_usuario WHERE documento_usuario = ? AND id_metodo_pago = ?";

        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, documento);
            ps.setInt(2, idMetodoPago);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    saldo = rs.getDouble("saldo");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener saldo: " + e.toString());
        }
        return saldo;
    }

   
    public boolean actualizarSaldo(int documento, int idMetodoPago, double nuevoSaldo) {
        String sql = "UPDATE cuenta_usuario SET saldo = ? WHERE documento_usuario = ? AND id_metodo_pago = ?";
        
        try (Connection con = Conexion.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setDouble(1, nuevoSaldo);
            ps.setInt(2, documento);
            ps.setInt(3, idMetodoPago);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar saldo: " + e.toString());
            return false;
        }
    }
}