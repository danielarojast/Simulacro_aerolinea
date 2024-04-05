package controller;

import entity.Avion;
import model.AvionModel;

import javax.swing.*;
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
}
