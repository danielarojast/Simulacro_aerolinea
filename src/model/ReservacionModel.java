package model;

import dataBase.CRUD;
import dataBase.ConfigDB;
import entity.Pasajero;
import entity.Reservacion;
import entity.Vuelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservacionModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection= ConfigDB.openConnection();
        Reservacion objReservacion = (Reservacion) obj;

        try {
            String sql= "INSERT INTO reservacion(fecha_reservacion, asiento, id_pasajero, id_vuelo) VALUE (?,?,?,?);";

            PreparedStatement objPrepare= objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objReservacion.getFecha_reservacion());
            objPrepare.setString(2, objReservacion.getAsiento());
            objPrepare.setInt(3, objReservacion.getId_pasajero());
            objPrepare.setInt(4, objReservacion.getId_vuelo());

            objPrepare.execute();
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while(objResult.next()){
                objReservacion.setId_reservacion(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Se agrego Reserva correctamente");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objReservacion;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection= ConfigDB.openConnection();
        ArrayList<Object> listReservacion= new ArrayList<>();

        try {
            String sql= "SELECT * FROM reservacion INNER JOIN pasajero ON reservacion.id_pasajero = pasajero.id_pasajero " +
                    "INNER JOIN vuelo ON reservacion.id_vuelo = vuelo.id_vuelo;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();
            while(objResult.next()){
                Reservacion objReservacion = new Reservacion();
                Pasajero objPasajero = new Pasajero();
                Vuelo objVuelo = new Vuelo();

              objReservacion.setId_reservacion(objResult.getInt("id_reservacion"));
              objReservacion.setFecha_reservacion(objResult.getString("fecha_reservacion"));
              objReservacion.setAsiento(objResult.getString("asiento"));
              objReservacion.setId_pasajero(objResult.getInt("id_pasajero"));
              objReservacion.setId_vuelo(objResult.getInt("id_vuelo"));

              objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
              objPasajero.setNombre(objResult.getString("nombre"));
              objPasajero.setApellido((objResult.getString("apellido")));
              objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));

              objVuelo.setId_vuelo(objResult.getInt("vuelo.id_vuelo"));
              objVuelo.setDestino(objResult.getString("vuelo.destino"));
              objVuelo.setFecha_salida(objResult.getString("vuelo.fecha_salida"));
              objVuelo.setHora_salida(objResult.getString("vuelo.hora_salida"));
              objVuelo.setId_avion(objResult.getInt("vuelo.id_avion"));

              objReservacion.setPasajero(objPasajero);
              objReservacion.setVuelo(objVuelo);

              listReservacion.add(objReservacion);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return listReservacion;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection= ConfigDB.openConnection();
        Reservacion objReservacion = (Reservacion) obj;
        boolean idUpdate = false;

        try {
            String sql= "UPDATE reservacion SET fecha_reservacion = ?, asiento = ?, id_pasajero = ?, id_vuelo = ? WHERE id_reservacion = ?;";

            PreparedStatement objPrepare= objConnection.prepareStatement(sql);

            objPrepare.setString(1, objReservacion.getFecha_reservacion());
            objPrepare.setString(2, objReservacion.getAsiento());
            objPrepare.setInt(3, objReservacion.getId_pasajero());
            objPrepare.setInt(4, objReservacion.getId_vuelo());
            objPrepare.setInt(5, objReservacion.getId_reservacion());

            int totalAcffectedRow = objPrepare.executeUpdate();

            if(totalAcffectedRow > 0){
               idUpdate = true;
               JOptionPane.showMessageDialog(null, "los datos fueron actualizados");
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return idUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection= ConfigDB.openConnection();
        Reservacion objReservacion = (Reservacion) obj;
        boolean idDelete = false;

        try {
            String sql= "DELETE FROM reservacion WHERE id_reservacion = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objReservacion.getId_reservacion());

            int totalAfectedRows= objPrepare.executeUpdate();
            System.out.println("no entro");
            if(totalAfectedRows > 0){
                System.out.println("si entro");
                idDelete = true;
                JOptionPane.showMessageDialog(null, "La reserva fue eliminada");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return idDelete;
    }

    public Reservacion findById(int id) {
        Connection objConnection= ConfigDB.openConnection();
        Reservacion objReservacion = null;

        try {
            String sql= "SELECT * FROM reservacion INNER JOIN pasajero ON reservacion.id_pasajero = pasajero.id_pasajero " +
                    "INNER JOIN vuelo ON reservacion.id_vuelo = vuelo.id_vuelo WHERE reservacion.id_reservacion = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();
            while(objResult.next()){
                objReservacion = new Reservacion();
                Pasajero objPasajero = new Pasajero();
                Vuelo objVuelo = new Vuelo();

                objReservacion.setId_reservacion(objResult.getInt("id_reservacion"));
                objReservacion.setFecha_reservacion(objResult.getString("fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("asiento"));
                objReservacion.setId_pasajero(objResult.getInt("id_pasajero"));
                objReservacion.setId_vuelo(objResult.getInt("id_vuelo"));

                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellido((objResult.getString("apellido")));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));

                objVuelo.setId_vuelo(objResult.getInt("vuelo.id_vuelo"));
                objVuelo.setDestino(objResult.getString("vuelo.destino"));
                objVuelo.setFecha_salida(objResult.getString("vuelo.fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("vuelo.hora_salida"));
                objVuelo.setId_avion(objResult.getInt("vuelo.id_avion"));

                objReservacion.setPasajero(objPasajero);
                objReservacion.setVuelo(objVuelo);

            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }

        return objReservacion;
    }

}
