/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

/**
 *
 * @author gfhgy
 */

import Modelo.Usuario;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDao {

    public List<Usuario> lista() {
        List<Usuario> datos = new ArrayList<>();

        String sql = "SELECT * FROM usuarios";

        try (
            Connection con = Conexion.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setDocumento(rs.getInt(1));
                u.setNombre(rs.getString(2));
                u.setDireccion(rs.getString(3));
                u.setTelefono(rs.getInt(4));
                u.setCorreo(rs.getString(5));
                datos.add(u);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error al Cargar Usuarios", JOptionPane.ERROR_MESSAGE);
        }
        return datos;
    }
    
    // Asumiré que en tu tabla `usuarios` tienes una columna llamada `contrasena`
public Usuario validarUsuario(String documento, String password) {
    Usuario usuario = null;
    String sql = "SELECT * FROM usuarios WHERE Documento = ? AND contrasena = ?";

    try (
        Connection con = Conexion.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
    ) {
        ps.setString(1, documento);
        ps.setString(2, password);
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                // Si encontramos un resultado, creamos el objeto Usuario
                usuario = new Usuario();
                usuario.setDocumento(rs.getInt("Documento"));
                usuario.setNombre(rs.getString("Nombre"));
                // Sigue añadiendo otros datos del usuario si los necesitas
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.toString(), "Error de Validación", JOptionPane.ERROR_MESSAGE);
    }
    
    return usuario; // Devuelve el objeto si el login es correcto, o null si es incorrecto
}
}
