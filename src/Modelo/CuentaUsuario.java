/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author gfhgy
 */
public class CuentaUsuario {
   private int idCuenta;
    private int documentoUsuario;
    private int idMetodoPago;
    private double saldo;

    // Getters y Setters
    public int getIdCuenta() { return idCuenta; }
    public void setIdCuenta(int idCuenta) { this.idCuenta = idCuenta; }
    public int getDocumentoUsuario() { return documentoUsuario; }
    public void setDocumentoUsuario(int documentoUsuario) { this.documentoUsuario = documentoUsuario; }
    public int getIdMetodoPago() { return idMetodoPago; }
    public void setIdMetodoPago(int idMetodoPago) { this.idMetodoPago = idMetodoPago; }
    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; } 
}
