package controller;

import entity.Avion;
import model.AvionModel;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class AvionController {
    public static void create(){
        AvionModel objAvionModel= new AvionModel();

        String modelo= JOptionPane.showInputDialog("Ingrese el modelo del avion: ");
        String capacidad= JOptionPane.showInputDialog("Capacidad: ");

        if(modelo != null || capacidad != null){
            Avion objAvion= new Avion();
            objAvion.setModelo(modelo);
            objAvion.setCapacidad(capacidad);

            objAvion= (Avion) objAvionModel.insert(objAvion);
            JOptionPane.showMessageDialog(null, objAvion.toString());
        }
    }

    public static void getAll(){
        AvionModel objAvionModel= new AvionModel();

        String listAvion = "Lista aviones:  \n";

        for(Object iterador: objAvionModel.findAll()){
           Avion objAvion = (Avion) iterador;
           listAvion += objAvion.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listAvion);
    }
    public static String getAllString() {
        AvionModel objAvionModel = new AvionModel();
        String listAvion = "Lista aviones:  \n";

        Avion objAvion;
        for (Iterator var2 = objAvionModel.findAll().iterator(); var2.hasNext(); listAvion = listAvion + objAvion.toString() + "\n") {
            Object iterador = var2.next();
            objAvion = (Avion) iterador;
        }

        return listAvion;
    }

    public static void findById() {
        AvionModel objAvionModel = new AvionModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa ID a buscar: "));
        Avion objAvion = objAvionModel.findById(id);

        if (objAvion == null) {
            JOptionPane.showMessageDialog(null, "No se encontro id");
        } else {
            System.out.println(objAvion.getId_avion());
            JOptionPane.showMessageDialog(null, objAvion.toString());

        }
    }

    public static void delete() {
        AvionModel objAvionModel = new AvionModel();
        String listAvion = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog("Ingresa ID a eliminar: "));
        Avion objAvion = objAvionModel.findById(idDelete);
        boolean confirm = true;

        if (objAvion == null) {
            JOptionPane.showMessageDialog(null, "No se encontro id");
        } else {
            int confirm2 = JOptionPane.showConfirmDialog((Component) null, "Are you sure want to delete the avion \n" + objAvion.toString());
            if (confirm2 == 0) {
                objAvionModel.delete(objAvion);
            }
        }
    }

    public static void update(){
        AvionModel objAvionModel = new AvionModel();
        int id= Integer.parseInt(JOptionPane.showInputDialog("Ingresa ID a editar: "));
        String listAvion= getAllString();
        Avion objAvion = objAvionModel.findById(id);

        if(objAvion == null){
            JOptionPane.showMessageDialog(null, "No se encontro id");
        }else{
            String modelo = JOptionPane.showInputDialog(null, "Inserte el modelo",objAvion.getModelo());
            String capacidad = JOptionPane.showInputDialog(null,"Inserte la capacidad",objAvion.getCapacidad());

            objAvion.setModelo(modelo);
            objAvion.setCapacidad(capacidad);

            objAvionModel.update(objAvion);
        }

    }
}
