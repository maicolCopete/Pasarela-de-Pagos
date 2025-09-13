/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author gfhgy
 */
import Dao.UsuarioDao;
import Main.Main; // Importar Main para llamar al método estático
import Modelo.Usuario;
import View.LoginVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LoginControlador implements ActionListener {

    private LoginVista vista;
    private UsuarioDao dao;

    public LoginControlador(LoginVista vista, UsuarioDao dao) {
        this.vista = vista;
        this.dao = dao;
        this.vista.btnIngresar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String doc = vista.txtDocumento.getText();
        String pass = new String(vista.txtPassword.getPassword());

        if (doc.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Debe ingresar documento y contraseña.");
            return;
        }

        Usuario usuarioValidado = dao.validarUsuario(doc, pass);

        if (usuarioValidado != null) {
            Main.iniciarAplicacionPrincipal(usuarioValidado); // Inicia la app principal
        } else {
 
            JOptionPane.showMessageDialog(vista, "Documento o contraseña incorrectos.");
        }
    }
}
