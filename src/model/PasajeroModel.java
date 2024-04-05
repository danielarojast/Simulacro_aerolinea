package model;

import dataBase.CRUD;
import dataBase.ConfigDB;
import entity.Pasajero;

import javax.swing.*;
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

            objPrepare.setString(1, "nombre");
            objPrepare.setString(2,"apellido");
            objPrepare.setString(3,"documento_identidad");

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
        return false;
    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }
}
