package Main;

import Controlador.Controlador;
import Controlador.LoginControlador;
import Dao.UsuarioDao;
import Factory.PagoFactory;
import Factory.TipoDePago;
import Modelo.Usuario;
import Strategy.PagoStrategy;
import View.Interfaz;
import View.LoginVista;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
     
        LoginVista vistaLogin = new LoginVista();
        
        
        UsuarioDao dao = new UsuarioDao(); 
        LoginControlador c = new LoginControlador(vistaLogin, dao);
        
        vistaLogin.setVisible(true);
    }
    
   
    public static void iniciarAplicacionPrincipal(Usuario usuarioLogueado) {
        Interfaz vistaPrincipal = new Interfaz();
        Controlador c = new Controlador(vistaPrincipal, usuarioLogueado);
        vistaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}