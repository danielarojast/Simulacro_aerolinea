package controller;

import entity.Pasajero;
import model.PasajeroModel;

import javax.swing.*;
import java.util.Iterator;

public class PasajeroController {

    public static void create(){
        PasajeroModel objPasajeroModel= new PasajeroModel();

        String nombre= JOptionPane.showInputDialog("Nombre: ");
        String apellido= JOptionPane.showInputDialog("Apellido: ");
        String documento_identidad= JOptionPane.showInputDialog("Documento de identidad");

        if(nombre != null || apellido != null ||documento_identidad != null){
            Pasajero objPasajero= new Pasajero();

            objPasajero.setNombre(nombre);
            objPasajero.setApellido(apellido);
            objPasajero.setDocumento_identidad(documento_identidad);

            objPasajero= (Pasajero) objPasajeroModel.insert(objPasajero);
            JOptionPane.showMessageDialog(null, objPasajero.toString());
        }
    }

    public static void getAll(){
        PasajeroModel objPasajeroModel= new PasajeroModel();

        String listPasajero= "Lista de pasajeros: "+ "\n";
        for(Object iterador: objPasajeroModel.findAll()){
            Pasajero objPasajero= (Pasajero) iterador;
            listPasajero += objPasajero.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listPasajero);
    }

    public static String getAllString() {
        PasajeroModel objModel = new PasajeroModel();
        String listPasajero = "Lista de pasajeros:  \n";

        Pasajero objPasajero;
        for (Iterator var2 = objModel.findAll().iterator(); var2.hasNext(); listPasajero = listPasajero + objPasajero.toString() + "\n") {
            Object iterador = var2.next();
            objPasajero = (Pasajero) iterador;
        }

        return listPasajero;
    }
}
