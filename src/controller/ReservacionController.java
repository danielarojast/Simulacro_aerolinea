package controller;

import entity.Pasajero;
import entity.Reservacion;
import entity.Vuelo;
import model.PasajeroModel;
import model.ReservacionModel;
import model.VueloModel;

import javax.swing.*;
import java.awt.*;

public class ReservacionController {

    public static void create(){
        ReservacionModel objReservacionModel = new ReservacionModel();
        PasajeroModel objPasajeroModel = new PasajeroModel();
        VueloModel objVueloModel = new VueloModel();

        String fecha_reservacion = JOptionPane.showInputDialog("Fecha de reservacion: ");
        String asiento = JOptionPane.showInputDialog("Asiento: ");
        String opcPasajero= JOptionPane.showInputDialog( "Ingrese el ID del pasajero: ");
        String opcVuelo = JOptionPane.showInputDialog("Ingrese el ID del vuelo");

        int id_pasajero= Integer.parseInt(opcPasajero);
        int id_vuelo= Integer.parseInt(opcVuelo);

        if(fecha_reservacion != null && asiento != null && opcPasajero != null && opcVuelo != null){

            if(objPasajeroModel.findById(id_pasajero) !=  null){
                if (objVueloModel.findById(id_vuelo) != null){

                    Reservacion objReservacion = new Reservacion();

                    objReservacion.setFecha_reservacion(fecha_reservacion);
                    objReservacion.setAsiento(asiento);
                    objReservacion.setId_pasajero(id_pasajero);
                    objReservacion.setId_vuelo(id_vuelo);

                    objReservacion= (Reservacion) objReservacionModel.insert(objReservacion);

                    objReservacion.setPasajero(objPasajeroModel.findById(id_pasajero));
                    objReservacion.setVuelo(objVueloModel.findById(id_vuelo));


                }else{
                    JOptionPane.showMessageDialog(null, "No se encontro id del vuelo");
                }
            }else{
                JOptionPane.showMessageDialog(null, "No se encontro id del pasajero");
            }
        }

    }

    public static void getAll(){
        ReservacionModel objReservacionModel = new ReservacionModel();
        String listReservas= "LISTA DE RESERVAS: ";

        for(Object iterador: objReservacionModel.findAll()){
            Reservacion objReservacion = (Reservacion) iterador;
            listReservas += objReservacion.toString();
        }
        JOptionPane.showMessageDialog(null, listReservas);
    }

    public static String getAllString(){
        ReservacionModel objReservacionModel = new ReservacionModel();
        String listReservas= "LISTA DE RESERVAS: ";

        for(Object iterador: objReservacionModel.findAll()){
            Reservacion objReservacion = (Reservacion) iterador;
            listReservas += objReservacion.toString() + "\n";
        }

        return listReservas;
    }

    public static void findById(){
        ReservacionModel objReservacionModel = new ReservacionModel();

        int idFind = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la reserva: "));
        Reservacion objReservacion = objReservacionModel.findById(idFind);

        if(objReservacion == null){
            JOptionPane.showMessageDialog(null, "No existe reserva con ese id");
        }else{
            JOptionPane.showMessageDialog(null, objReservacion.toString());
        }
    }

    public static void upDate(){

        ReservacionModel objReservacionModel = new ReservacionModel();
        String listReservas= getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listReservas + "Ingrese el ID de la reserva que desea modificar: "));
        Reservacion objReservacion = objReservacionModel.findById(idUpdate);
        boolean confirm = true;

        if(objReservacion == null){
            JOptionPane.showMessageDialog(null, "No existe reserva con ese id");
        }else{

            String fecha_reservacion = JOptionPane.showInputDialog(null, "Fecha de reservacion: ", objReservacion.getFecha_reservacion());
            String asiento = JOptionPane.showInputDialog(null, "Asiento: ", objReservacion.getAsiento());
            int id_pasajero = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese ID del pasajero: " + objReservacion.getId_pasajero())) ;
            int id_vuelo = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese ID del vuelo: " + objReservacion.getId_vuelo()));

            objReservacion.setFecha_reservacion(fecha_reservacion);
            objReservacion.setAsiento(asiento);
            objReservacion.setId_pasajero(id_pasajero);
            objReservacion.setId_vuelo(id_vuelo);

            objReservacionModel.update(objReservacion);
        }
    }

    public static void delete(){
        ReservacionModel objReservacionModel = new ReservacionModel();
        String listReservas= getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listReservas + "Ingrese el ID de la reserva a eliminar: "));
        Reservacion objReservacion = objReservacionModel.findById(idDelete);

        if(objReservacion == null){
            JOptionPane.showMessageDialog(null, "No existe reserva con ese id");
        }else{
            int confirm = JOptionPane.showConfirmDialog((Component) null,"Estas seguro que deseas eliminar la reserva \n" + objReservacion.toString());
            if(confirm == 0){
                objReservacionModel.delete(objReservacion);
            }
        }
    }
}
