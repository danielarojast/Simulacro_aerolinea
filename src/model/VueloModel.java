package model;

import dataBase.CRUD;
import dataBase.ConfigDB;
import entity.Avion;
import entity.Pasajero;
import entity.Vuelo;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VueloModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vuelo objVuelo = (Vuelo) obj;

        try{
            String sql= "INSERT INTO vuelo(destino, fecha_salida, hora_salida, id_avion) VALUE (?,?,?,?);";

            PreparedStatement objPrepare= objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objVuelo.getDestino());
            objPrepare.setString(2, objVuelo.getFecha_salida());
            objPrepare.setString(3, objVuelo.getHora_salida());
            objPrepare.setInt(4, objVuelo.getId_avion());

            objPrepare.execute();
            ResultSet objResult= objPrepare.getGeneratedKeys();

            while(objResult.next()){
                objVuelo.setId_vuelo(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Se agrego el Vuelo correctamente");


        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objVuelo;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        ArrayList<Object> listVuelo= new ArrayList<>();

        try{
            String sql= "SELECT * FROM vuelo INNER JOIN avion ON vuelo.id_avion = avion.id_avion;";
            PreparedStatement objPrepare= objConnection.prepareStatement(sql);

            ResultSet objResult= objPrepare.executeQuery();

            while(objResult.next()){
                //6.1 Crear un Vuelo
                Vuelo objVuelo= new Vuelo();
                Avion objAvion= new Avion();

                //6.2 Llenar el objero con la informacion de la base de datos
                objVuelo.setId_vuelo(objResult.getInt("vuelo.id_vuelo"));
                objVuelo.setDestino(objResult.getString("vuelo.destino"));
                objVuelo.setFecha_salida(objResult.getString("vuelo.fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("vuelo.hora_salida"));
                objVuelo.setId_avion(objResult.getInt("vuelo.id_avion"));

                objAvion.setId_avion(objResult.getInt("id_avion"));
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setCapacidad(objResult.getString("capacidad"));

                objVuelo.setAvion(objAvion);

                //6.3 Agregamos el coder a la lista
                listVuelo.add(objVuelo);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return listVuelo;
    }

    public boolean update(Object obj) {
        Connection objConnector= ConfigDB.openConnection();
        Vuelo objVuelo= (Vuelo) obj;
        boolean idUpdate= false;

        try {
            String sql= "UPDATE vuelo SET destino = ?, fecha_salida = ?, hora_salida = ?, id_avion = ? WHERE id_vuelo = ?;";
            PreparedStatement objPrepare = objConnector.prepareStatement(sql);

            objPrepare.setString(1, objVuelo.getDestino());
            objPrepare.setString(2, objVuelo.getFecha_salida());
            objPrepare.setString(3, objVuelo.getHora_salida());
            objPrepare.setInt(4,objVuelo.getId_avion() );
            objPrepare.setInt(5,objVuelo.getId_vuelo() );

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
        Vuelo objVuelo= (Vuelo) obj;
        boolean idDelete= false;

        try {
            String sql= "DELETE FROM vuelo WHERE id_vuelo= ?;";
            PreparedStatement objPrepare= objConnector.prepareStatement(sql);
            objPrepare.setInt(1, objVuelo.getId_vuelo());

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

    public Vuelo findById(int id){
        Connection objConnection= ConfigDB.openConnection();
        Vuelo objVuelo= null;

        try{
            String sql= "SELECT * FROM vuelo INNER JOIN avion ON vuelo.id_avion = avion.id_avion WHERE vuelo.id_vuelo = ? ;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult = objPrepare.executeQuery();



            if (objResult.next()){
                objVuelo= new Vuelo();
                Avion objAvion = new Avion();

                objVuelo.setDestino(objResult.getString("destino"));
                objVuelo.setFecha_salida(objResult.getString("fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("hora_salida"));
                objVuelo.setId_avion(objResult.getInt("id_avion"));
                objVuelo.setId_vuelo(objResult.getInt("id_vuelo"));

                objAvion.setId_avion(objResult.getInt("id_avion"));
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setCapacidad(objResult.getString("capacidad"));

                objVuelo.setAvion(objAvion);


            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objVuelo;
    }
}
