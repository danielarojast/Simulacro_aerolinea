package model;

import dataBase.CRUD;
import dataBase.ConfigDB;
import entity.Pasajero;

import javax.swing.*;
import java.awt.*;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasajeroModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection= ConfigDB.openConnection();
        Pasajero objPasajero= (Pasajero) obj;

        try{
            String sql= "INSERT INTO pasajero(nombre, apellido, documento_identidad) VALUE (?,?,?);";
            PreparedStatement objPrepare= objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objPasajero.getNombre());
            objPrepare.setString(2,objPasajero.getApellido());
            objPrepare.setString(3,objPasajero.getDocumento_identidad());

            objPrepare.execute();

            ResultSet objResult= objPrepare.getGeneratedKeys();

            while(objResult.next()){
                objPasajero.setId_pasajero(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Se agrego el pasajero correctamente");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objPasajero;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection= ConfigDB.openConnection();
        ArrayList<Object> listPasajero= new ArrayList<>();

        try{
            String sql= "SELECT * FROM pasajero;";

            PreparedStatement objPrepare= objConnection.prepareStatement(sql);
            ResultSet objResult= objPrepare.executeQuery();

            while(objResult.next()){
                Pasajero objPasajero= new Pasajero();

                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellido((objResult.getString("apellido")));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));

                System.out.println(objPasajero.getNombre());
                listPasajero.add(objPasajero);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return listPasajero;
    }

    @Override
   public boolean update(Object obj) {
        Connection objConnector= ConfigDB.openConnection();
        Pasajero objPasajero= (Pasajero) obj;
        boolean idUpdate= false;

        try {
            String sql= "UPDATE pasajero SET nombre = ?, apellido = ?, documento_identidad = ? WHERE id_pasajero = ?;";
            PreparedStatement objPrepare = objConnector.prepareStatement(sql);

            objPrepare.setString(1, objPasajero.getNombre());
            objPrepare.setString(2, objPasajero.getApellido());
            objPrepare.setString(3, objPasajero.getDocumento_identidad());
            objPrepare.setInt(4,objPasajero.getId_pasajero() );

            int allAfectedRow= objPrepare.executeUpdate();
            if(allAfectedRow > 0){
                idUpdate= true;
                JOptionPane.showMessageDialog((Component) null, "the update was successful.");
            }

        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return idUpdate;
    }

     @Override
    public boolean delete(Object obj) {
        Connection objConnector= ConfigDB.openConnection();
        Pasajero objPasajero= (Pasajero) obj;
        boolean idDelete= false;

        try {
            String sql= "DELETE FROM pasajero WHERE id_pasajero= ?;";
            PreparedStatement objPrepare= objConnector.prepareStatement(sql);
            objPrepare.setInt(1, objPasajero.getId_pasajero());

            int totalAfectedRows= objPrepare.executeUpdate();
            if(totalAfectedRows > 0){
                idDelete = true;
                JOptionPane.showMessageDialog((Component) null, "the update was successful.");
            }

        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return idDelete;
    }

    public Pasajero findById(int id){
         Connection objConnection= ConfigDB.openConnection();
        Pasajero objPasajero= null;

         try{
             String sql= "SELECT * FROM pasajero WHERE id_pasajero = ? ;";
             PreparedStatement objPrepare = objConnection.prepareStatement(sql);
             objPrepare.setInt(1,id);
             ResultSet objResult = objPrepare.executeQuery();

             while(objResult.next()){
                 objPasajero= new Pasajero();

                 objPasajero.setNombre(objResult.getString("nombre"));
                 objPasajero.setApellido(objResult.getString("apellido"));
                 objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));
                 objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));

             }
         }catch (SQLException e){
             JOptionPane.showMessageDialog(null, e.getMessage());
         }finally {
             ConfigDB.closeConnection();
         }
        return objPasajero;
    }

    public Pasajero findByDocumento(String documento){
        Connection objConnection= ConfigDB.openConnection();
        Pasajero objPasajero= null;

        try{
            String sql= "SELECT * FROM pasajero WHERE documento_identidad LIKE ? ;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, documento);
            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                objPasajero= new Pasajero();

                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellido(objResult.getString("apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));
                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));

            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objPasajero;
    }
}
