package controller;

import entity.Vuelo;
import model.AvionModel;
import model.VueloModel;

import javax.swing.*;

public class VueloController {

    public static void create(){
        VueloModel objVueloModel = new VueloModel();

        String destino = JOptionPane.showInputDialog("Ingrese destino del vuelo: ");
        String fecha = JOptionPane.showInputDialog("Ingrese fecha del vuelo: ");
        String hora = JOptionPane.showInputDialog("Ingrese hora del vuelo: ");
        String id_avion1= JOptionPane.showInputDialog(AvionController.getAllString() + "\n Ingrese el ID del avion en el que se va hacer el vuelo: ");


        //esta validacion es por si le da cancelar antes de llenar los datos no aparezca error
        if(destino != null && fecha != null && hora!= null && id_avion1 != null){
            int id_avion = Integer.parseInt(id_avion1) ;
            AvionModel objAvionModel = new AvionModel();

            //Validar si si esta el id del avion

            if(objAvionModel.findById(id_avion) == null){
                JOptionPane.showMessageDialog(null, "El avion no existe, no se puede crear vuelo sin avion.");
            }else{

                System.out.println("ENTRÉ ");
                Vuelo objVuelo = new Vuelo();
                objVuelo.setDestino(destino);
                objVuelo.setFecha_salida(fecha);
                objVuelo.setHora_salida(hora);
                objVuelo.setId_avion(id_avion);

                objVuelo = (Vuelo) objVueloModel.insert(objVuelo);

            }
        }
    }

    public static void getAll(){
        VueloModel objVueloModel= new  VueloModel();

        String listVuelo= "Lista de  Vuelos: "+ "\n";
        for(Object iterador: objVueloModel.findAll()){
            Vuelo objVuelo = (Vuelo) iterador;
            listVuelo += objVuelo.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listVuelo);
    }

    public static String getAllString(){
        VueloModel objVueloModel = new VueloModel();

        String listVuelo = "Lista de Vuelo\n";

        for (Object iterator : objVueloModel.findAll()){
            Vuelo objVuelo = (Vuelo) iterator;
            listVuelo += objVuelo.toString() + "\n";
        }
        return listVuelo;
    }
}
