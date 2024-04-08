import controller.AvionController;
import controller.PasajeroController;
import controller.ReservacionController;
import controller.VueloController;
import dataBase.ConfigDB;
import entity.Pasajero;
import entity.Vuelo;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //ConfigDB.openConnection();

        String option = "";

        do {
            option = JOptionPane.showInputDialog("""
                    opciones: 
                    1. Aviones
                    2. Pasajeros
                    3. Vuelos
                    4. Reservas
                    5. Salir
                                        
                    Selecciona una opcion: 
                    """);

            switch (option) {
                case "1":
                    //AVIONES
                    String option1 = "";

                    do {
                        option1 = JOptionPane.showInputDialog("""
                                opciones: 
                                1. Insertar nuevo avion
                                2. Listar todos los aviones
                                3. Buscar por id
                                4. Eliminar 
                                5. Editar
                                6. Salir
                                                        
                                Selecciona una opcion: 
                                """);
                        switch (option1) {
                            case "1":
                                AvionController.create();
                                break;
                            case "2":
                                AvionController.getAll();
                                break;
                            case"3":
                                AvionController.findById();
                                break;
                            case"4":
                                AvionController.delete();
                                break;
                            case"5":
                                AvionController.update();
                                break;
                        }
                    } while (!option1.equals("6"));
                    break;
                case"2":
                    //PASAJEROS
                    String option2 = "";

                    do {
                        option2 = JOptionPane.showInputDialog("""
                                opciones: 
                                1. Insertar nuevo pasajero
                                2. Listar todos los pasajeros
                                3. Buscar por id
                                4. Buscar por Documento de identidad
                                5. Eliminar 
                                6. Editar
                                7. Salir
                                                        
                                Selecciona una opcion: 
                                """);
                        switch (option2) {
                            case "1":
                                PasajeroController.create();
                                break;
                            case"2":
                                PasajeroController.getAll();
                                break;
                            case"3":
                                PasajeroController.findById();
                                break;
                            case"4":
                                PasajeroController.findByDocumento();
                                break;
                            case"5":
                                PasajeroController.delete();
                                break;
                            case"6":
                                PasajeroController.update();
                                break;

                        }
                    }while(!option2.equals("7"));

                    break;
                case"3":
                    //VUELOS
                    String option3= "";
                    do {
                        option3 = JOptionPane.showInputDialog("""
                                opciones: 
                                1. Insertar nuevo vuelo
                                2. Listar todos los vuelo
                                3. Buscar por id
                                4. Eliminar 
                                5. Editar
                                6. Salir
                                                        
                                Selecciona una opcion: 
                                """);
                        switch (option3) {
                            case "1":
                                VueloController.create();
                                break;
                            case"2":
                                VueloController.getAll();
                                break;
                            case"3":
                                VueloController.findById();
                                break;
                            case"4":
                                VueloController.delete();
                                break;
                            case"5":
                                VueloController.update();
                                break;
                        }
                    }while(!option3.equals("6"));

                case"4":
                    //RESERVAS
                    String option4= "";
                    do {
                        option4 = JOptionPane.showInputDialog("""
                                opciones: 
                                1. Insertar nueva reserva
                                2. Listar todas las reservas
                                3. Buscar por id
                                4. Eliminar 
                                5. Editar
                                6. Salir
                                                        
                                Selecciona una opcion: 
                                """);
                        switch (option4) {
                            case "1":
                                ReservacionController.create();
                                break;
                            case "2":
                                ReservacionController.getAll();
                                break;
                            case "3":
                                ReservacionController.findById();
                                break;
                            case "4":
                                ReservacionController.delete();
                                break;
                            case "5":
                                ReservacionController.upDate();
                                break;

                        }
                    }while(!option4.equals("6"));

                    break;
            }

        } while (!option.equals("5"));
    }
}