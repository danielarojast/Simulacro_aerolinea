package model;

import dataBase.CRUD;
import dataBase.ConfigDB;
import entity.Avion;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvionModel implements CRUD {
    @Override
    public Object insert(Object obj) {

        //abrir conexion
        Connection objConnection = ConfigDB.openConnection();
        Avion objAvion= (Avion) obj;

        try{
            String sql= "INSERT INTO avion(id_avion, modelo, capacidad) VALUES (?,?,?) ;";

            PreparedStatement objPrepare= objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1,objAvion.getId_avion());
            objPrepare.setString(2, objAvion.getModelo());
            objPrepare.setString(3,objAvion.getCapacidad());

            objPrepare.execute();

            //7. Obtener el resultado con los id o llaves generadas
            ResultSet objRest= objPrepare.getGeneratedKeys();

            //8. Iterar mientras  haya un registro
            while (objRest.next()){
                objAvion.setId_avion(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "el avion se agrego correctamente");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally {
            //cerrar conexion
            ConfigDB.closeConnection();
        }
        return objAvion;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        ArrayList<Object> listAvion= new ArrayList<>();

        try{
            String sql= "SELECT * FROM avion;";
            PreparedStatement objPrepare= objConnection.prepareStatement(sql);

            ResultSet objResult= objPrepare.executeQuery();

            while(objResult.next()){
                //6.1 Crear un coder
                Avion objAvion= new Avion();

                //6.2 Llenar el objero con la informacion de la base de datos
                objAvion.setId_avion(objResult.getInt("id_avion"));
                objAvion.setModelo(objResult.getString("modelo"));
                objAvion.setCapacidad(objResult.getString("capacidad"));

                //6.3 Agregamos el coder a la lista
                listAvion.add(objAvion);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, error.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return listAvion;
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
