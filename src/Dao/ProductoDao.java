    package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Modelo.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProductoDao {
    
    public List<Producto> lista() {
        List<Producto> datos = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (
            Connection con = Conexion.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();   
        ) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdP(rs.getInt(1));
                p.setNombreP(rs.getString(2));             
                p.setPrecioP(rs.getFloat(3));
                p.setCantP(rs.getInt(4));
                datos.add(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error de Consulta", JOptionPane.ERROR_MESSAGE);
        }
        return datos;
    }
    
    public int agregar(Producto p) {
    int r;
    String sql = "INSERT INTO productos VALUES (?,?,?,?)"; 
    
    try (
        Connection con = Conexion.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
    ) {
        ps.setInt(1, p.getIdP());
        ps.setString(2, p.getNombreP());
        ps.setFloat(3, p.getPrecioP());
        ps.setInt(4, p.getCantP());
        
        r = ps.executeUpdate();
        
        return r;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.toString(), "Error de Consulta", JOptionPane.ERROR_MESSAGE);
        return 0;
    }
}
}
