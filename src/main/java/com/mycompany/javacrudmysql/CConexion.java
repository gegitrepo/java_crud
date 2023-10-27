/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javacrudmysql;

import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.DriverManager;


/**
 *
 * @author chalo
 */
public class CConexion {
    
    Connection conectar = null;
    
    String usuario = "Gonzalo";
    String contrasena = "GO1025GO";
    String bd = "bdescuela";
    String ip = "172.172.92.27";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection estableceConexion() {
    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,contrasena);
           // JOptionPane.showMessageDialog(null, "La coneccion a sido Exitosa.");

        }
        catch (Exception e){
        JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos, error: " + e.toString());
        }
        return conectar;
    }


                
    
    
    
}
