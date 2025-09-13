package View;

import javax.swing.*;
import java.awt.*;

public class LoginVista extends JFrame {
    
    // Componentes que el controlador necesitará
    public JTextField txtDocumento;
    public JPasswordField txtPassword;
    public JButton btnIngresar;

    public LoginVista() {
        setTitle("Inicio de Sesión");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setLayout(new GridLayout(3, 2, 10, 10)); // Diseño simple de rejilla

        // Crear y añadir los componentes
        add(new JLabel("Documento:"));
        txtDocumento = new JTextField();
        add(txtDocumento);

        add(new JLabel("Contraseña:"));
        txtPassword = new JPasswordField();
        add(txtPassword);

        add(new JLabel()); // Espacio vacío
        btnIngresar = new JButton("Ingresar");
        add(btnIngresar);
    }
}