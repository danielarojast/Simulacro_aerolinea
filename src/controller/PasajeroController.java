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

    public static String getAllString(){
        PasajeroModel objPasajeroModel = new PasajeroModel();

        String listPasajero = "Lista de Pasajero\n";

        for (Object iterator : objPasajeroModel.findAll()){
            Pasajero objPasajero = (Pasajero) iterator;
            listPasajero += objPasajero.toString() + "\n";
        }
        return listPasajero;
    }

    public static void findById() {
        PasajeroModel objPasajeroModel= new PasajeroModel();

        int id= Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID a buscar: ")) ;

        Pasajero objPasajero= objPasajeroModel.findById(id);

        if(objPasajero == null){
            JOptionPane.showMessageDialog(null, "No se encontro id");
        }else{
            JOptionPane.showMessageDialog(null,objPasajero.toString());
        }
    }

    public static void delete() {
        PasajeroModel objPasajeroModel= new PasajeroModel();

        int id= Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID a eliminar: ")) ;
        String listPasajero= getAllString();
        Pasajero objPasajero= objPasajeroModel.findById(id);

        if(objPasajero == null){
            JOptionPane.showMessageDialog(null, "No se encontro id");
        }else{
            int confirm2= JOptionPane.showConfirmDialog(null, "Esta seguro que lo desea eliminar? "+ objPasajero.toString()) ;
            if(confirm2 == 0){
                objPasajeroModel.delete(objPasajero);
            }
        }
    }

    public static void update() {
        PasajeroModel objPasajeroModel= new PasajeroModel();
        String listPasajero= getAllString();
        int id= Integer.parseInt(JOptionPane.showInputDialog(listPasajero + "Ingrese el ID a editar: ")) ;

        Pasajero objPasajero= objPasajeroModel.findById(id);

        if(objPasajero == null){
            JOptionPane.showMessageDialog(null, "No se encontro id");
        }else{
            String nombre= JOptionPane.showInputDialog(null, "Ingrese nombre ", objPasajero.getNombre());
            String apellido = JOptionPane.showInputDialog(null, "Ingrese apellido ", objPasajero.getApellido());
            String documento_identidad= JOptionPane.showInputDialog(null,"Ingrese documento de identidad ", objPasajero.getDocumento_identidad());

            objPasajero.setNombre(nombre);
            objPasajero.setApellido(apellido);
            objPasajero.setDocumento_identidad(documento_identidad);

            objPasajeroModel.update(objPasajero);
        }
    }

    public static void findByDocumento() {
        PasajeroModel objPasajeroModel= new PasajeroModel();

        String documento= JOptionPane.showInputDialog("Ingrese el documento de identidad: ");

        Pasajero objPasajero= objPasajeroModel.findByDocumento(documento);

        if(objPasajero == null){
            JOptionPane.showMessageDialog(null, "No se encontro pasajero con ese documento");
        }else{
            JOptionPane.showMessageDialog(null,objPasajero.toString());
        }
    }

}
