/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javacrudmysql;

import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author chalo
 */
public class CAlumnos {
    
    int codigo;
    String nombreAlumnos;
    String apellidosAlumnos;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreAlumnos() {
        return nombreAlumnos;
    }

    public void setNombreAlumnos(String nombreAlumnos) {
        this.nombreAlumnos = nombreAlumnos;
    }

    public String getApellidosAlumnos() {
        return apellidosAlumnos;
    }

    public void setApellidosAlumnos(String apellidosAlumnos) {
        this.apellidosAlumnos = apellidosAlumnos;
    }
    
    public void insertarAlumno(JTextField paramNombres, JTextField paramApellidos){
        
        setNombreAlumnos(paramNombres.getText());
        setApellidosAlumnos(paramApellidos.getText());
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "insert into alumnos (nombres,apellidos) values (?,?);";
        
        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1, getNombreAlumnos());
            cs.setString(2, getApellidosAlumnos());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se inserto correctamente el alumno.");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"No se inserto correctamente el alumno, Error: "+ e.toString());
        }
    }
    
    public void MostrarAlumnos(JTable paramTablaTotalAlumnos){
        
        CConexion objetoConexion = new CConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        TableRowSorter<TableModel> OrdenarTabla = new  TableRowSorter<TableModel>(modelo);
        paramTablaTotalAlumnos.setRowSorter(OrdenarTabla);
        
        String sql = "";
        
        modelo.addColumn("ID");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        
        paramTablaTotalAlumnos.setModel(modelo);
              
        sql = "select * from alumnos;";
        String[] datos = new String[3];
        Statement st;
        
        try {
            st = objetoConexion.estableceConexion().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                
                modelo.addRow(datos);           
            }
            paramTablaTotalAlumnos.setModel(modelo);
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"No se pudo mostrar los registros. Error: "+ e.toString());
            
        }
     }
    
    public void SeleccionarAlumno(JTable paramTablaAlumnos, JTextField paramId, JTextField paramNombres, JTextField paramApellidos){
        
        try {
            int fila = paramTablaAlumnos.getSelectedRow();
            if (fila >=0){
                paramId.setText((paramTablaAlumnos.getValueAt(fila,0).toString()));
                paramNombres.setText((paramTablaAlumnos.getValueAt(fila,1).toString()));
                paramApellidos.setText((paramTablaAlumnos.getValueAt(fila,2).toString()));
            }
            else 
            {
             JOptionPane.showMessageDialog(null,"Fila no seleccionada");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error de seleccion. Error: "+ e.toString());
        }
    
    }
    
    public void ModificarAlumnos(JTextField paramCodigo, JTextField paramNombres, JTextField paramApellidos){
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setNombreAlumnos(paramNombres.getText());
        setApellidosAlumnos(paramApellidos.getText());
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "update alumnos set alumnos.nombres = ?, alumnos.apellidos = ? where alumnos.id = ?; ";
        
        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1,getNombreAlumnos());
            cs.setString(2,getApellidosAlumnos());
            cs.setInt(3,getCodigo());
         
            cs.execute();
            JOptionPane.showMessageDialog(null,"Modificacion Exitosa.");
                        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"No se Modifico. Error: "+ e.toString());
        }
    }
  
    public void EliminarAlumnos(JTextField paramCodigo){
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        
        CConexion objetoConexion = new CConexion();
        
        String consulta ="delete from alumnos where alumnos.id =?;";
        
        try {
              CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setInt(1,getCodigo());
         
            cs.execute();
            JOptionPane.showMessageDialog(null,"Se ha eliminado exitosamente el registro.");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"No se pudo eliminar. Error: "+e.toString());

            
        }
        
    
    
    }


}
    
    

